package kraechz.app.repositories;

import java.util.List;

import kraechz.app.models.Kraechz;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class KraechzRepository {
  private final JdbcTemplate db;

  public KraechzRepository (JdbcTemplate db) {
    this.db = db;
  }

  public List<Kraechz> alle() {
    var allQuery = """
            SELECT handle, text FROM kraechz_posts
            """;

    return db.query(allQuery, new DataClassRowMapper<>(Kraechz.class));
  }

  public List<Kraechz> filtereNachHandle(String name) {
    var filterQuery = """
              SELECT p.handle, p.text FROM kraechz_posts p
              WHERE p.handle = ?
            """;

    return db.query(filterQuery, new DataClassRowMapper<>(Kraechz.class), name);
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
