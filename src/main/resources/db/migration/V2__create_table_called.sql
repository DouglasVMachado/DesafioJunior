CREATE TABLE calleds(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    priority VARCHAR(255) NOT NULL,
    opening_date TIMESTAMP NOT NULL,
    last_updated TIMESTAMP NOT NULL
)