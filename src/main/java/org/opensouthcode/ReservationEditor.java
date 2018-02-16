package org.opensouthcode;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringComponent
@UIScope
public class ReservationEditor extends VerticalLayout {

	private static final long serialVersionUID = 6251462789734087062L;

	private final ReservationRepository ReservationRepository;

	private Reservation reservation;

	TextField reservationOwner = new TextField("Reservation Owner");
	TextField restaurantName = new TextField("Restaurant");
	DateField reservationLocalDate = new DateField("Reservation Date");
	TextField dinersNumber = new TextField("Diners Number");
	CheckBox confirmed = new CheckBox("Reservation Confirmed", false);

	Button save = new Button("Save", VaadinIcons.DISC);
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcons.TRASH);
	CssLayout actions = new CssLayout(save, cancel, delete);

	Binder<Reservation> binder = new Binder<>(Reservation.class);

	@Autowired
	public ReservationEditor(ReservationRepository repository) {
		this.ReservationRepository = repository;

		addComponents(reservationOwner, restaurantName, reservationLocalDate, dinersNumber, confirmed, actions);

		binder.bindInstanceFields(this);

		//reservationOwner.setCaption("Reservation Owner: First and Last Name");

		setSpacing(true);
		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		save.addClickListener(e -> repository.save(reservation));
		delete.addClickListener(e -> repository.delete(reservation));
		cancel.addClickListener(e -> editReservation(reservation));
		setVisible(false);
	}

	public interface ChangeHandler {

		void onChange();
	}

	public final void editReservation(Reservation c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			reservation = ReservationRepository.findOne(c.getId());
		} else {
			reservation = c;
		}
		cancel.setVisible(persisted);

		binder.setBean(reservation);

		setVisible(true);

		save.focus();
		reservationOwner.selectAll();
	}

	public void setChangeHandler(ChangeHandler h) {
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}

}
