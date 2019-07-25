package com.stackroute.keepnote.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Reminder;

@Service
public class ReminderServiceImpl implements ReminderService {

	private ReminderDAO reminderDAO;

	@Autowired
	public ReminderServiceImpl(ReminderDAO reminderDAO) {
		this.reminderDAO = reminderDAO;
	}

	@Override
	public boolean createReminder(Reminder reminder) {

		boolean status = reminderDAO.createReminder(reminder);
		return status;
	}

	@Override
	public Reminder updateReminder(Reminder reminder, int id) throws ReminderNotFoundException {

		Reminder updateReminder = reminderDAO.getReminderById(id);

		updateReminder.setReminderName(reminder.getReminderName());
		updateReminder.setReminderDescription(reminder.getReminderDescription());
		updateReminder.setReminderType(reminder.getReminderType());
		updateReminder.setReminderCreatedBy(updateReminder.getReminderCreatedBy());
		updateReminder.setReminderCreationDate(new Date());

		boolean status = reminderDAO.updateReminder(updateReminder);

		if (!status) {

			return null;
		}

		return updateReminder;
	}

	@Override
	public boolean deleteReminder(int reminderId) {
		boolean status = reminderDAO.deleteReminder(reminderId);
		return status;
	}

	@Override
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {

		return reminderDAO.getReminderById(reminderId);
	}

	@Override
	public List<Reminder> getAllReminderByUserId(String userId) {

		return reminderDAO.getAllReminderByUserId(userId);
	}
}
