CREATE DATABASE IF NOT EXISTS entrance_gate;
GRANT ALL PRIVILEGES ON entrance_gate.* TO 'user'@'%';

CREATE DATABASE IF NOT EXISTS ticket;
GRANT ALL PRIVILEGES ON ticket.* TO 'user'@'%';

CREATE DATABASE IF NOT EXISTS park_ops;
GRANT ALL PRIVILEGES ON park_ops.* TO 'user'@'%';

CREATE DATABASE IF NOT EXISTS park_info_sys;
GRANT ALL PRIVILEGES ON park_info_sys.* TO 'user'@'%';
