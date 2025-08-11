package com.example.backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.Entity.Note;
import com.example.backend.Entity.NoteStatus;
import com.example.backend.Repository.NoteRepository;

@Service
public class NoteSeviceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // private final BCryptPasswordEncoder passwordEncoder = new
    // BCryptPasswordEncoder();

    @Override
    public Note createNote(String title, String content) {
        Note note = new Note(title, content);
        // If content is empty, keep as draft
        if (content == null || content.trim().isEmpty()) {
            note.setStatus(NoteStatus.DRAFT);
        }
        return noteRepository.save(note);
    }

    @Override
    public Note saveNote(Long id, String title, String content) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitle(title);
            note.setContent(content);
            note.setStatus(NoteStatus.SAVED);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findByStatusAndIsDeletedFalseAndIsArchivedFalse(NoteStatus.SAVED);
    }

    @Override
    public List<Note> getAllDrafts() {
        return noteRepository.findByStatusAndIsDeletedFalse(NoteStatus.DRAFT);
    }

    @Override
    public List<Note> getAllArchivedNotes() {
        return noteRepository.findByIsArchivedTrueAndIsDeletedFalse();
    }

    @Override
    public List<Note> getAllPrivateNotes() {
        return noteRepository.findByIsPrivateTrueAndIsDeletedFalseAndIsArchivedFalse();
    }

    @Override
    public List<Note> getAllDeletedNotes() {
        return noteRepository.findByIsDeletedTrue();
    }

    @Override
    public Note archiveNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setArchived(true);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public Note unarchiveNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setArchived(false);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public Note makeNotePrivate(Long id, String password) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setPrivate(true);
            // note.setPrivatePassword(passwordEncoder.encode(password));
            note.setPrivatePassword(password);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public Note makeNotePublic(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setPrivate(false);
            note.setPrivatePassword(null);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    // @Override
    // public boolean verifyPrivateNotePassword(Long id, String password) {
    // Optional<Note> optionalNote = noteRepository.findById(id);
    // if (optionalNote.isPresent()) {
    // Note note = optionalNote.get();
    // if (note.isPrivate() && note.getPrivatePassword() != null) {
    // return passwordEncoder.matches(password, note.getPrivatePassword());
    // }
    // }
    // return false;
    // }

    @Override
    public Note deleteNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setDeleted(true);
            note.setDeletedAt(LocalDateTime.now());
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public Note restoreNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setDeleted(false);
            note.setDeletedAt(null);
            return noteRepository.save(note);
        }
        throw new RuntimeException("Note not found with id: " + id);
    }

    @Override
    public void permanentlyDeleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    @Scheduled(cron = "0 0 2 * * ?") // Run daily at 2 AM
    public void cleanupOldDeletedNotes() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<Note> oldDeletedNotes = noteRepository.findNotesDeletedBefore(thirtyDaysAgo);
        noteRepository.deleteAll(oldDeletedNotes);
    }

    @Override
    public List<Note> searchNotes(String searchTerm) {
        return noteRepository.searchNotes(searchTerm);
    }

    @Override
    public List<Note> getAllNoteEntity() {
        return noteRepository.findAll();
    }

}
