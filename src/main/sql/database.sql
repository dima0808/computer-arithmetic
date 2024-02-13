-- Database: d49r83d9q2hkvk

-- DROP DATABASE IF EXISTS d49r83d9q2hkvk;

CREATE DATABASE d49r83d9q2hkvk
    WITH
    OWNER = iqnbdiysrdssht
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

ALTER DATABASE d49r83d9q2hkvk
    SET search_path TO "$user", public, heroku_ext;

GRANT ALL ON DATABASE d49r83d9q2hkvk TO iqnbdiysrdssht;