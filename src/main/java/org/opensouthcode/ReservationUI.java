package org.opensouthcode;

import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;

@SpringUI
public class ReservationUI extends UI {

	private static final long serialVersionUID = -158427528697976789L;

	private final ReservationRepository ReservationRepo;

	private final ReservationEditor ReservationEditor;

	final Grid<Reservation> ReservationGrid;

	final TextField ReservationFilter;

	private final Button addNewReservationBtn;

	@Autowired
	public ReservationUI(ReservationRepository repo, ReservationEditor editor) {
		this.ReservationRepo = repo;
		this.ReservationEditor = editor;
		this.ReservationGrid = new Grid<>(Reservation.class);
		this.ReservationFilter = new TextField();
		this.addNewReservationBtn = new Button("New Reservation", VaadinIcons.PLUS);
	}

	@Override
	protected void init(VaadinRequest request) {
		HorizontalLayout filterBtnLayout = new HorizontalLayout(ReservationFilter, addNewReservationBtn);
		VerticalLayout leftLayout = new VerticalLayout(filterBtnLayout, ReservationGrid);
		VerticalLayout rightLayout = new VerticalLayout(ReservationEditor);
		HorizontalLayout mainLayout = new HorizontalLayout(leftLayout, rightLayout);
		setContent(mainLayout);

		ReservationGrid.setHeight(400, Unit.PIXELS);
		ReservationGrid.setWidth(900, Unit.PIXELS);
		ReservationGrid.setColumns("id", "reservationOwner", "restaurantName", "dinersNumber", "confirmed");
		Column<Reservation, Date> reservationDateColumn = ReservationGrid.addColumn(Reservation::getReservationDate,
				new DateRenderer("%1$tb %1$td, %1$tY", Locale.getDefault()));
		reservationDateColumn.setCaption("Reservation Date");

		ReservationFilter.setWidth(350, Unit.PIXELS);
		ReservationFilter.setPlaceholder("Filter by Reservation Owner");

		ReservationFilter.setValueChangeMode(ValueChangeMode.LAZY);
		ReservationFilter.addValueChangeListener(e -> listReservations(e.getValue()));

		ReservationGrid.asSingleSelect().addValueChangeListener(e -> {
			ReservationEditor.editReservation(e.getValue());
		});

		addNewReservationBtn.addClickListener(e -> ReservationEditor.editReservation(new Reservation("", "",
				new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), "", false)));

		ReservationEditor.setChangeHandler(() -> {
			ReservationEditor.setVisible(false);
			listReservations(ReservationFilter.getValue());
		});

		listReservations(null);
	}

	void listReservations(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			ReservationGrid.setItems((Collection<Reservation>) ReservationRepo.findAll());
		} else {
			ReservationGrid.setItems(ReservationRepo.findByReservationOwnerContainsIgnoreCase(filterText));
		}
	}

}
