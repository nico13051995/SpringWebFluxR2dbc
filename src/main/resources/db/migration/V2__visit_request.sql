CREATE TABLE public.bc_visit_request
(
    id bigserial NOT NULL,
    visit_date time without time zone,
    description character varying(500),
    user_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT user_visit_request FOREIGN KEY (user_id)
        REFERENCES public.bc_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.bc_visit_request
    OWNER to postgres;