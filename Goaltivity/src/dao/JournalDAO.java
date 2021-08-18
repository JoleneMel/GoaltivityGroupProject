package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entity.Journal;
public class JournalDAO {
	private Connection connection;
	private JournalTagsDAO journalTagsDAO = new JournalTagsDAO();
	private final String GET_JOURNALS_QUERY = "SELECT * FROM journal";
	private final String GET_JOURNAL_BY_ID_QUERY = "SELECT * FROM journal WHERE id = ?";
	private final String CREATE_NEW_JOURNAL_QUERY = "INSERT INTO journal (title, content, user) VALUES (?,?,?)";
	private final String DELETE_JOURNAL_BY_ID_QUERY = "DELETE FROM journal WHERE id =?";
	private final String UPDATE_JOURNAL_BY_ID_QUERY = "UPDATE journal SET content = ? WHERE id=?";
	private final String GET_JOURNAL_ID_QUERY = "SELECT id FROM journal WHERE title = ?";
	private final String GET_JOURNALENTRIES_BY_TAGID_QUERY = "SELECT * FROM journal INNER JOIN journal_tags ON journal.id = journal_tags.journal WHERE journal_tags.tag = ?";
	public List<Journal> getJournalEntriesByJournalTag(int tagId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_JOURNALENTRIES_BY_TAGID_QUERY);
		ps.setInt(1, tagId);
		ResultSet rs = ps.executeQuery();
		List<Journal> journalsByTag = new ArrayList<Journal>();
		while (rs.next()) {
			journalsByTag
					.add(populateJournal(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
		}
		return journalsByTag;
	}
	public JournalDAO() {
		connection = DBConnection.getConnection();
	}
	public List<Journal> getJournals() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_JOURNALS_QUERY).executeQuery();
		List<Journal> journals = new ArrayList<Journal>();
		while (rs.next()) {
			journals.add(populateJournal(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
		}
		return journals;
	}
	public Journal getJournalbyId(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_JOURNAL_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateJournal(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getInt(5));
	}
	public int readGetJournalId(String title) throws SQLException {
		int journalId = 0;
		PreparedStatement ps = connection.prepareStatement(GET_JOURNAL_ID_QUERY);
		ps.setString(1, title);
		ResultSet rs = ps.executeQuery();
		rs.next();
		journalId = rs.getInt(1);
		return journalId;
	}
	public void createNewJournal(String entryName, String content, int journalTagId, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_JOURNAL_QUERY);
		ps.setString(1, entryName);
		ps.setString(2, content);
		ps.setInt(3, id);
		ps.executeUpdate();
		journalTagsDAO.createNewJournalTag(readGetJournalId(entryName), journalTagId);
	}
	public void deleteJournalById(int id) throws SQLException {
		journalTagsDAO.deleteJournalTagByJournalId(id);
		PreparedStatement ps = connection.prepareStatement(DELETE_JOURNAL_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	public void updateJournalById(int id, String content) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_JOURNAL_BY_ID_QUERY);
		ps.setInt(2, id);
		ps.setString(1, content);
		ps.executeUpdate();
	}
	private Journal populateJournal(int id, Date date, String title, String content, int userId) throws SQLException {
		return new Journal(id, date, title, content, userId);
	}
}