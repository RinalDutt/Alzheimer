package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Entity.Note;
import com.example.backend.Entity.NoteStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Get all saved notes (not deleted, not archived)
    List<Note> findByStatusAndIsDeletedFalseAndIsArchivedFalse(NoteStatus status);

    // Get all draft notes (not deleted)
    List<Note> findByStatusAndIsDeletedFalse(NoteStatus status);

    // Get all archived notes (not deleted)
    List<Note> findByIsArchivedTrueAndIsDeletedFalse();

    // Get all private notes (not deleted, not archived)
    List<Note> findByIsPrivateTrueAndIsDeletedFalseAndIsArchivedFalse();

    // Get all deleted notes
    List<Note> findByIsDeletedTrue();

    // Find notes deleted before a certain date (for permanent deletion)
    @Query("SELECT n FROM Note n WHERE n.isDeleted = true AND n.deletedAt < :date")
    List<Note> findNotesDeletedBefore(@Param("date") LocalDateTime date);

    // Search notes by title or content
    @Query("SELECT n FROM Note n WHERE n.isDeleted = false AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(n.content) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Note> searchNotes(@Param("searchTerm") String searchTerm);
}