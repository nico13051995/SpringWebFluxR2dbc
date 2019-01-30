-- Table: public.tbl_user

-- DROP TABLE public.tbl_user;

CREATE TABLE public.tbl_user
(
     id SERIAL PRIMARY KEY,
    name text
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;