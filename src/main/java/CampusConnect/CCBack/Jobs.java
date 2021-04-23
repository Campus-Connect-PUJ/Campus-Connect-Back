package CampusConnect.CCBack;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import CampusConnect.CCBack.Service.EventualidadService;

@Component
@EnableAsync
public class Jobs {

    @Autowired
    EventualidadService eService;

    @Async
    // correr esto cada dia a media noche
    @Scheduled(cron = "0 0 * * * ?")
    // @Scheduled(cron = "* * * * * ?")
    public void cleanEventualidadesTaskAsync() throws InterruptedException {
        System.out.println(
            "Task async: eliminando eventualidades viejas " + LocalDate.now());

        // TODO: agregar lista de excepciones
        eService.deleteEventualidadesViejas(1);
    }

}
