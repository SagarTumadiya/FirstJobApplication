package com.example.firstjobapp.job;
import com.example.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
        private JobService jobService;
        public JobController(JobService jobService){
            this.jobService = jobService;
        }
        @GetMapping
        public ResponseEntity<List<Job>>findAll(){
            return ResponseEntity.ok(jobService.findAll());
        }
        @PostMapping
        public ResponseEntity<String> createJob(@RequestBody Job job){
            jobService.createJob(job);

            return new ResponseEntity<>("Job added successfully",HttpStatus.OK);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Job> getJobById(@PathVariable Long id){
           Job job = jobService.getJobById(id);
           if(job!=null)
               return new ResponseEntity<>(job, HttpStatus.OK);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteJob(@PathVariable Long id){
            boolean delete = jobService.deleteJobById(id);
            if(delete)
                return new ResponseEntity<>("Job deleted Successfully",HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @PutMapping("/{id}")
        //@RequestMapping(value = "/jobs/{id}",method = RequestMethod.PUT)
        public ResponseEntity<String> updateJob(@PathVariable Long id,@RequestBody Job updatedJob){
            boolean updated = jobService.updateJob(id, updatedJob);
            if(updated)
                return new ResponseEntity<>("Job updated Successfully",HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}