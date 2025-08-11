package com.example.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.Entity.Note;
import com.example.backend.Service.NoteService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
// @CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Create a new note
    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Map<String, String> request) {
        try {
            String title = request.get("title");
            String content = request.get("content");
            Note note = noteService.createNote(title, content);
            return new ResponseEntity<>(note, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Save/Update a note
    @PutMapping("/save/{id}")
    public ResponseEntity<Note> saveNote(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String title = request.get("title");
            String content = request.get("content");
            Note note = noteService.saveNote(id, title, content);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        if (note.isPresent()) {
            return new ResponseEntity<>(note.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // Get all saved notes
    @GetMapping("/saved")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/allnotes")
    public List<Note> getAllNoteEntity() {
        return noteService.getAllNoteEntity();
    }

    // Get all draft notes
    @GetMapping("/drafts")
    public ResponseEntity<List<Note>> getAllDrafts() {
        List<Note> drafts = noteService.getAllDrafts();
        return new ResponseEntity<>(drafts, HttpStatus.OK);
    }

    // Get all archived notes
    @GetMapping("/archived")
    public ResponseEntity<List<Note>> getAllArchivedNotes() {
        List<Note> archivedNotes = noteService.getAllArchivedNotes();
        return new ResponseEntity<>(archivedNotes, HttpStatus.OK);
    }

    // Get all private notes
    @GetMapping("/private")
    public ResponseEntity<List<Note>> getAllPrivateNotes() {
        List<Note> privateNotes = noteService.getAllPrivateNotes();
        return new ResponseEntity<>(privateNotes, HttpStatus.OK);
    }

    // Get all deleted notes
    @GetMapping("/deleted")
    public ResponseEntity<List<Note>> getAllDeletedNotes() {
        List<Note> deletedNotes = noteService.getAllDeletedNotes();
        return new ResponseEntity<>(deletedNotes, HttpStatus.OK);
    }

    // Archive a note
    @PutMapping("/archive/{id}")
    public ResponseEntity<Note> archiveNote(@PathVariable Long id) {
        try {
            Note note = noteService.archiveNote(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Unarchive a note
    @PutMapping("/unarchive/{id}")
    public ResponseEntity<Note> unarchiveNote(@PathVariable Long id) {
        try {
            Note note = noteService.unarchiveNote(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Make note private
    @PutMapping("/make-private/{id}")
    public ResponseEntity<Note> makeNotePrivate(@PathVariable Long id, @RequestBody Map<String, String> request) {
        try {
            String password = request.get("password");
            Note note = noteService.makeNotePrivate(id, password);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Make note public
    @PutMapping("/make-public/{id}")
    public ResponseEntity<Note> makeNotePublic(@PathVariable Long id) {
        try {
            Note note = noteService.makeNotePublic(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Verify private note password
    // @PostMapping("/verify-password/{id}")
    // public ResponseEntity<Map<String, Boolean>>
    // verifyPrivateNotePassword(@PathVariable Long id,
    // @RequestBody Map<String, String> request) {
    // String password = request.get("password");
    // boolean isValid = noteService.verifyPrivateNotePassword(id, password);
    // return new ResponseEntity<>(Map.of("valid", isValid), HttpStatus.OK);
    // }

    // Soft delete a note
    @PutMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id) {
        try {
            Note note = noteService.deleteNote(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Restore a deleted note
    @PutMapping("/restore/{id}")
    public ResponseEntity<Note> restoreNote(@PathVariable Long id) {
        try {
            Note note = noteService.restoreNote(id);
            return new ResponseEntity<>(note, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Permanently delete a note
    @DeleteMapping("/permanent/{id}")
    public ResponseEntity<Map<String, String>> permanentlyDeleteNote(@PathVariable Long id) {
        try {
            noteService.permanentlyDeleteNote(id);
            return new ResponseEntity<>(Map.of("message", "Note permanently deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Failed to delete note"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Search notes
    @GetMapping("/search")
    public ResponseEntity<List<Note>> searchNotes(@RequestParam String query) {
        List<Note> notes = noteService.searchNotes(query);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
