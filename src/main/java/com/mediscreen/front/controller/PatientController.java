package com.mediscreen.front.controller;

import com.mediscreen.front.model.Patient;
import com.mediscreen.front.proxies.PatientsProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PatientController {

    private final PatientsProxy patientsProxy;

    public PatientController(PatientsProxy patientsProxy) {
        this.patientsProxy = patientsProxy;
    }

    @GetMapping("/patient/add")
    public String addPatient(final Model model) {
        model.addAttribute("patient", new Patient());
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            patientsProxy.addPatient(patient);
            model.addAttribute("patient", patientsProxy.getAllPatients());
            return "redirect:/patient/list";
        }
        return "add";
    }

    @GetMapping("/patient/list")
    public String home(final Model model) {
        model.addAttribute("patients", patientsProxy.getAllPatients());
        return "patient/list";
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Patient patient = patientsProxy.getPatientById(id).orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable Integer id, @Valid Patient patient, BindingResult result, Model
            model) {
        if (result.hasErrors()) {
            return "patient/update";
        }
        patientsProxy.updatePatient(patient);
        model.addAttribute("patients", patientsProxy.getAllPatients());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Integer id, Model model) {
        patientsProxy.deletePatientById(id);
        return "redirect:/patient/list";
    }
}
