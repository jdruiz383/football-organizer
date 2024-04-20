package com.footballorg.controllers;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.footballorg.interfaces.TeamRepository;
import com.footballorg.models.Team;

import jakarta.transaction.Transactional;

@RestController
public class CalendarController {

    @Autowired
    TeamRepository er;

    @GetMapping("/api")
    @ResponseBody
    String home() {
        return "Welcome!";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Team> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDate end) {
        return er.findBetween(start, end);
    }
    
    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Team createEvent(@RequestBody EventCreateParams params) {

        Team e = new Team();
        e.setName(params.name);
        e.setStart(params.start);
        e.setTime(params.time);
        e.setText(params.text);
        er.save(e);

        return e;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Team moveEvent(@RequestBody EventMoveParams params) {

        Team e = er.findById(params.id).get();
        e.setStart(params.start);
        e.setTime(params.time);
        e.setText(params.text);
        er.save(e);

        return e;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Team setColor(@RequestBody SetColorParams params) {

        Team e = er.findById(params.id).get();
        e.setColor(params.color);
        er.save(e);

        return e;
    }

    @PostMapping("/api/events/delete")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventDeleteResponse deleteEvent(@RequestBody EventDeleteParams params) {

        er.deleteByText(params.text);

        return new EventDeleteResponse() {{
            message = "Deleted";
        }};
    }
    
    @PostMapping("/api/events/edit")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    EventDeleteResponse editEvent(@RequestBody EventDeleteParams params) {

        er.findByText(params.text);

        return new EventDeleteResponse() {{
            message = "Game Updated";
        }};
    }

    public static class EventDeleteParams {
        public String text;
    }

    public static class EventDeleteResponse {
        public String message;
    }

    public static class EventCreateParams {
        public String name;
        public String league;
        public String conference;
        public LocalDate start;
        public LocalTime time;
        public String text;
    }

    public static class EventMoveParams {
        public Long id;
        public String name;
        public String league;
        public String conference;
        public LocalDate start;
        public LocalTime time;
        public String text;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }


}
