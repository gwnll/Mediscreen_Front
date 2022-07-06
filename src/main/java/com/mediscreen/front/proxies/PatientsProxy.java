package com.mediscreen.front.proxies;

import com.mediscreen.front.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "patients", url = "localhost:8081")
public interface PatientsProxy {

    @PostMapping("/patient/validate")
    void addPatient(@RequestBody Patient patient);

    @GetMapping("/patient/list")
    List<Patient> getAllPatients();

    @GetMapping("patient/getPatientById/{id}")
    Optional<Patient> getPatientById(@PathVariable Integer id);

    @PostMapping("patient/updatePatient")
    void updatePatient(@RequestBody Patient patient);

    @GetMapping("/patient/delete/{id}")
    void deletePatientById(@PathVariable Integer id);
}
