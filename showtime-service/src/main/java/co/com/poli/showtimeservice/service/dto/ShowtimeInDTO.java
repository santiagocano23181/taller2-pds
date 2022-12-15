package co.com.poli.showtimeservice.service.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ShowtimeInDTO {
    private Long id;
    private Date date;
    private List<Long> moviesId;
}
