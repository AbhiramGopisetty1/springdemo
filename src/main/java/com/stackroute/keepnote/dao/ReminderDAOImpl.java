package com.stackroute.keepnote.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

@Repository
@Transactional
public class ReminderDAOImpl implements ReminderDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public ReminderDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean createReminder(Reminder reminder) {
		reminder.setReminderCreationDate(new Date());
		sessionFactory.getCurrentSession().save(reminder);
		return true;
	}

	@Override
	public boolean updateReminder(Reminder reminder) {

		try {
			sessionFactory.getCurrentSession().update(reminder);
			return true;
		}

		catch (Exception e) {
			return false;

		}
	}

	@Override
	public boolean deleteReminder(int reminderId) {

		try {
			Reminder reminder = sessionFactory.getCurrentSession().get(Reminder.class, reminderId);
			sessionFactory.getCurrentSession().delete(reminder);
			sessionFactory.getCurrentSession().flush();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {
		Reminder reminder = sessionFactory.getCurrentSession().get(Reminder.class, reminderId);
		if (reminder != null) {
			return reminder;
		} else {

			throw new ReminderNotFoundException("Reminder does not exists");
		}

	}

	@Override
	public List<Reminder> getAllReminderByUserId(String userId) {

		List<Reminder> list = sessionFactory.getCurrentSession().createQuery(
				"from Reminder where reminderCreatedBy= " + "'" + userId + "'" + "order by reminderCreationDate asc").list();
		return list;
	}

}
