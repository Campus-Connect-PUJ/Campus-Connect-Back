package CampusConnect.CCBack.Test;

import static java.util.stream.Collectors.toCollection;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CampusConnect.CCBack.Model.Eventualidad;
import CampusConnect.CCBack.Repository.EventualidadRepository;

@Service
public class EventualidadService {

    @Autowired
    private EventualidadRepository repository;

}
