package com.mediscreen.front.controller;

import com.mediscreen.front.model.Patient;
import com.mediscreen.front.proxies.NotesProxy;
import com.mediscreen.front.proxies.PatientsProxy;
import com.mediscreen.front.proxies.ReportsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReportController {
    private final NotesProxy notesProxy;
    private final PatientsProxy patientsProxy;
    private final ReportsProxy reportsProxy;

    public ReportController(NotesProxy notesProxy, PatientsProxy patientsProxy, ReportsProxy reportsProxy) {
        this.notesProxy = notesProxy;
        this.patientsProxy = patientsProxy;
        this.reportsProxy = reportsProxy;
    }

    @GetMapping("/{patientId}/report")
    public String generateReport(@PathVariable int patientId, Model model) {
        Patient patient = patientsProxy.getPatientById(patientId).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + patientId));
        model.addAttribute("patient", patient);
        model.addAttribute("notes", notesProxy.getAllNotes(patientId));
        model.addAttribute("riskLevel", reportsProxy.generateReport(patientId));
        return "report/report";
    }
}
