CREATE TABLE guideline(
  id VARCHAR(36) NOT NULL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255),
  start_session DATETIME(6),
  end_session DATETIME(6),
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL,
  deleted_at DATETIME(6)
);

CREATE TABLE voting_session(
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    guideline_id VARCHAR(36) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    upvote BOOLEAN,
    FOREIGN KEY (guideline_id) REFERENCES guideline(id)
);