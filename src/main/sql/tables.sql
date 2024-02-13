-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(70) COLLATE pg_catalog."default" NOT NULL,
    creditnumber integer,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    enabled smallint NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (username),
    CONSTRAINT users_email_key UNIQUE (email)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to iqnbdiysrdssht;


-- Table: public.authorities

-- DROP TABLE IF EXISTS public.authorities;

CREATE TABLE IF NOT EXISTS public.authorities
(
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    authority character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT authorities_username_authority_key UNIQUE (username, authority),
    CONSTRAINT authorities_username_fkey FOREIGN KEY (username)
    REFERENCES public.users (username) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.authorities
    OWNER to iqnbdiysrdssht;
