CREATE TABLE public.bc_user
(
    id bigserial NOT NULL,
    name character varying(100),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.bc_user
    OWNER to postgres;