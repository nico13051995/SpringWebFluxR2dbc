-- Table: public.bc_visit_request

-- DROP TABLE public.bc_visit_request;

CREATE TABLE public.bc_visit_request
(
    id bigint NOT NULL DEFAULT nextval('bc_visit_request_id_seq'::regclass),
    user_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone,
    visit_date timestamp without time zone NOT NULL,
    CONSTRAINT bc_visit_request_pkey PRIMARY KEY (id),
    CONSTRAINT user_visit_request FOREIGN KEY (id)
        REFERENCES public.bc_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.bc_visit_request
    OWNER to postgres;