package com.stackroute.keepnote.service;

import java.util.List;

import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Reminder;

public interface ReminderService {

	public boolean createReminder(Reminder reminder);

	public Reminder updateReminder(Reminder reminder, int id) throws ReminderNotFoundException;

	public boolean deleteReminder(int reminderId);

	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException;

	public List<Reminder> getAllReminderByUserId(String userId);
}
