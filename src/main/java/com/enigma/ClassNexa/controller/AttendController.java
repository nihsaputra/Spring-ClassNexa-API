package com.enigma.ClassNexa.controller;

import com.enigma.ClassNexa.entity.Attend;
import com.enigma.ClassNexa.model.WebResponse;
import com.enigma.ClassNexa.model.request.AttendRequest;
import com.enigma.ClassNexa.model.request.SearchAttendRequest;
import com.enigma.ClassNexa.model.request.UpdateAttendRequest;
import com.enigma.ClassNexa.model.response.AttendResponse;
import com.enigma.ClassNexa.model.response.SingleAttendResponse;
import com.enigma.ClassNexa.service.AttendService;
import com.enigma.ClassNexa.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttendController {
    private final AttendService attendService;
    private final AttendanceService attendanceService;
    @PostMapping(path = "/api/attend")
    public ResponseEntity<?> createAttend(@RequestBody AttendRequest request){
        AttendResponse attendResponse = attendService.create(request);
        WebResponse<AttendResponse> response = WebResponse.<AttendResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("success")
                .data(attendResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED ).body(response);
    }
    @GetMapping(path = "/api/attend")
    public ResponseEntity<?> getAll(@RequestParam(required = false, defaultValue = "1")Integer page,
                                    @RequestParam(required = false, defaultValue = "10")Integer size,
                                    @RequestParam(required = false) Timestamp classStartedAt,
                                    @RequestParam(required = false) String participantName){
        SearchAttendRequest searchAttendRequest = SearchAttendRequest.builder()
                .page(page)
                .size(size)
                .classStartedAt(classStartedAt)
                .participantName(participantName)
                .build();
        List<SingleAttendResponse> allWithFilter = attendService.getAllWithoutFilter(searchAttendRequest);
        WebResponse<List<SingleAttendResponse>> response = WebResponse.<List<SingleAttendResponse>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("success")
                .data(allWithFilter)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping(path = "/api/attend/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        SingleAttendResponse attendById = attendService.getAttendById(id);
        WebResponse<SingleAttendResponse> response = WebResponse.<SingleAttendResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("success")
                .data(attendById)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping(path = "/api/attend/{scheduleId}")
    public ResponseEntity<?> getAllWithFilter(@PathVariable String scheduleId){
        SingleAttendResponse attendById = attendService.getAttendById(scheduleId);
        WebResponse<SingleAttendResponse> response = WebResponse.<SingleAttendResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("success")
                .data(attendById)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping(path = "/api/attend/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        attendService.deleteById(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("success")
                .data("deleted")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping(path = "/api/attend")
    public ResponseEntity<?> update(@RequestBody UpdateAttendRequest request){
        SingleAttendResponse update = attendService.Update(request);
        WebResponse<SingleAttendResponse> response = WebResponse.<SingleAttendResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("success")
                .data(update)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
