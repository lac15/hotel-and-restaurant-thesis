package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.Date;

@ManagedBean(name = "reserveRoomScheduleBean")
public class ReserveRoomScheduleMB {

    @EJB
    private RoomService roomService;

    private RoomVo room;

    private ScheduleModel reservationModel;

    @PostConstruct
    public void init() {
        reservationModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                for (Date reservedDate : roomService.getRoomByNumber(room.getNumber()).getReservedDates()) {
                    addEvent(new DefaultScheduleEvent("Reserved", reservedDate, reservedDate, true));
                }
            }
        };
    }

    public RoomVo getRoom() {
        return room;
    }

    public void setRoom(RoomVo room) {
        this.room = room;
    }

    public ScheduleModel getReservationModel() {
        return reservationModel;
    }

    public void setReservationModel(ScheduleModel reservationModel) {
        this.reservationModel = reservationModel;
    }
}
