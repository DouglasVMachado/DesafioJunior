CREATE TABLE client_called (
    client_id INTEGER
    called_id INTEGER
    CONSTRAINT fk_client_called_client FOREIGN KEY(client_id) REFERENCES client(id)
    CONSTRAINT fk_client_called_called FOREIGN KEY(called_id) REFERENCES called(id)
)
