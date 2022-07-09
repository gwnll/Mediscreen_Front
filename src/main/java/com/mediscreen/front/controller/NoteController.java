package com.mediscreen.front.controller;

import com.mediscreen.front.model.Note;
import com.mediscreen.front.model.Patient;
import com.mediscreen.front.proxies.NotesProxy;
import com.mediscreen.front.proxies.PatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NoteController {
    private final NotesProxy notesProxy;
    private final PatientsProxy patientsProxy;

    public NoteController(NotesProxy notesProxy, PatientsProxy patientsProxy) {
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
    }

    @GetMapping("/{patientId}/note/add")
    public String addNote(@PathVariable Integer patientId, final Model model) {
        Patient patient = patientsProxy.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
        model.addAttribute("patientName", patient.getFirstName() + "&nbsp;" + patient.getLastName());
        Note note = new Note();
        note.setPatientId(patientId);
        model.addAttribute("note", note);
        return "note/add";
    }

    @PostMapping("/{patientId}/note/validate")
    public String validate(@PathVariable Integer patientId, @Valid Note note, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            notesProxy.addNote(note);
            model.addAttribute("notes", notesProxy.getAllNotes(patientId));
            return "redirect:/{patientId}/note/list";
        }
        return "add";
    }

    @GetMapping("/{patientId}/note/list")
    public String home(@PathVariable Integer patientId, final Model model) {
        Patient patient = patientsProxy.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
        model.addAttribute("patientId", patientId);
        model.addAttribute("patientName", patient.getFirstName() + "&nbsp;" + patient.getLastName());
        model.addAttribute("notes", notesProxy.getAllNotes(patientId));
        return "note/list";
    }

    @GetMapping("/{patientId}/note/update/{noteId}")
    public String showUpdateForm(@PathVariable("noteId") String noteId, @PathVariable("patientId") int patientId, Model model) {
        Note note = notesProxy.getNoteById(noteId).orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + noteId));
        Patient patient = patientsProxy.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
        model.addAttribute("patientName", patient.getFirstName() + "&nbsp;" + patient.getLastName());
        model.addAttribute("note", note);
        return "note/update";
    }

    @PostMapping("/{patientId}/note/update")
    public String updateNote(@PathVariable Integer patientId, @Valid Note note, BindingResult result, Model
            model) {
        if (result.hasErrors()) {
            return "note/update";
        }
        notesProxy.updateNote(note);
        model.addAttribute("notes", notesProxy.getAllNotes(patientId));
        return "redirect:/{patientId}/note/list";
    }

    @GetMapping("/{patientId}/note/delete/{noteId}")
    public String deleteNote(@PathVariable Integer patientId, @PathVariable String noteId, Model model) {
        notesProxy.deleteNoteById(noteId);
        model.addAttribute("notes", notesProxy.getAllNotes(patientId));
        return "redirect:/{patientId}/note/list";
    }
}
