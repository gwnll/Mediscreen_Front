package com.mediscreen.front.proxies;

import com.mediscreen.front.model.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "notes", url = "localhost:8082")
public interface NotesProxy {

    @PostMapping("/note/addNote")
    void addNote(@RequestBody Note note);

    @GetMapping("/note/{noteId}")
    Optional<Note> getNoteById(@PathVariable String noteId);

    @GetMapping("/{patientId}/note/list")
    List<Note> getAllNotes(@PathVariable Integer patientId);

    @PostMapping("/note/update")
    void updateNote(@RequestBody Note note);

    @GetMapping("/note/delete/{noteId}")
    void deleteNoteById(@PathVariable String noteId);

}
