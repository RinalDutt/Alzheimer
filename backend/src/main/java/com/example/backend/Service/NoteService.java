package com.example.backend.Service;

import java.util.List;
import java.util.Optional;

import com.example.backend.Entity.Note;

public interface NoteService {

    // Create a new note
    Note createNote(String title, String content);

    // Save/Update a note
    Note saveNote(Long id, String title, String content);

    // Get note by ID
    Optional<Note> getNoteById(Long id);

    // Get all notes (saved, not deleted, not archived)
    List<Note> getAllNotes();

    // Get all draft notes
    List<Note> getAllDrafts();

    // Get all archived notes
    List<Note> getAllArchivedNotes();

    // Get all private notes
    List<Note> getAllPrivateNotes();

    // Get all deleted notes
    List<Note> getAllDeletedNotes();

    // Archive a note
    Note archiveNote(Long id);

    // Unarchive a note
    Note unarchiveNote(Long id);

    // Make note private with password
    Note makeNotePrivate(Long id, String password);

    // Make note public (remove private)
    Note makeNotePublic(Long id);

    // Verify private note password
    // boolean verifyPrivateNotePassword(Long id, String password);

    // Soft delete a note
    Note deleteNote(Long id);

    // Restore a deleted note
    Note restoreNote(Long id);

    // Permanently delete a note
    void permanentlyDeleteNote(Long id);

    // Clean up notes deleted more than 30 days ago
    void cleanupOldDeletedNotes();

    // Search notes
    List<Note> searchNotes(String searchTerm);

    List<Note> getAllNoteEntity();
}