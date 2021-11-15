package kraechz.app.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kraechz.app.models.Kraechz;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class KraechzRepository {
  private final JdbcTemplate db;

  public KraechzRepository (JdbcTemplate db) {
    this.db = db;
  }

  private static Kraechz mapKraechz(ResultSet rs, int rowNum) throws SQLException {
    String handle = rs.getString(1);
    String text = rs.getString(2);
    return new Kraechz(handle, text);
  }

  public List<Kraechz> alle() {
    var allQuery = """
            SELECT handle, text FROM kraechz_posts
            """;

    return db.query(allQuery, KraechzRepository::mapKraechz);
  }

  public List<Kraechz> filtereNachHandle(String name) {
    var filterQuery = """
              SELECT p.handle, p.text FROM kraechz_posts p
              WHERE p.handle = ?
            """;

    return db.query(filterQuery, KraechzRepository::mapKraechz, name);
  }

  public void save(Kraechz k) {
    var saveQuery = """
            INSERT INTO kraechz_posts (handle, text) VALUES(
              ?, ?
            )
            """;

    db.update(saveQuery, k.handle(), k.text());
  }
}
