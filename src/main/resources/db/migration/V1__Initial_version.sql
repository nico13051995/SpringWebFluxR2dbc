-- Table: public.bc_user

-- DROP TABLE public.bc_user;

CREATE TABLE public.bc_user
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bc_user
    OWNER to postgres;