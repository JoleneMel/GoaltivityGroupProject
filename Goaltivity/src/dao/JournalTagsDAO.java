package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.JournalTags;
public class JournalTagsDAO {
	private Connection connection;
	private final String GET_JOURNALTAGS_QUERY = "SELECT * FROM tags";
	private final String GET_JOURNALTAG_BY_JOURNALID_QUERY = "SELECT * FROM journal_tags WHERE id = ?";
	private final String GET_JOURNALTAG_BY_TAGID_QUERY = "SELECT * FROM journal_tags WHERE journal_tagsId = ?";
	private final String CREATE_NEW_JOURNALTAG_QUERY = "INSERT INTO journal_tags(journal, tag) VALUES (?,?)";
	private final String DELETE_JOURNALTAG_BY_ID_QUERY = "DELETE FROM journal_tags where id = ?";
	private final String DELETE_JOURNALTAG_BY_JOURNALID_QUERY = "DELETE FROM journal_tags where journal = ?";
	
	public JournalTagsDAO() {
		connection = DBConnection.getConnection();
	}
	public List<JournalTags> getJournalTags() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_JOURNALTAGS_QUERY).executeQuery();
		List<JournalTags> journalTags = new ArrayList<JournalTags>();
		while (rs.next()) {
			journalTags.add(populateJournalTags(rs.getInt(1), rs.getInt(2)));
		}
		return journalTags;
	}
	public JournalTags getJournalTagByJournalId(int journalId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_JOURNALTAG_BY_JOURNALID_QUERY);
		ps.setInt(1, journalId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateJournalTags(rs.getInt(1), rs.getInt(2));
	}
	public void createNewJournalTag(int journalId, int tagId) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_JOURNALTAG_QUERY);
		ps.setInt(1, journalId);
		ps.setInt(2, tagId);
		ps.executeUpdate();
	}
	private JournalTags populateJournalTags(int journalTagId, int tagId) {
		return new JournalTags(journalTagId, tagId);
	}
	public void deleteJournalTag(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_JOURNALTAG_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	public void deleteJournalTagByJournalId(int journal) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_JOURNALTAG_BY_JOURNALID_QUERY);
		ps.setInt(1, journal);
		ps.executeUpdate();
	}
}

