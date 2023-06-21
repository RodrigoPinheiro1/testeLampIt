create table IF NOT EXISTS public.user_system (
    id bigint(20) NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(100) NOT NULL,
    PRIMARY KEY (ID)
);

INSERT INTO public.user_system(id,name,email,username,password)
    SELECT 1,'admin', 'admin@admin.com', 'admin','$2a$10$JHBxLrpvVTOdjF7gwlE8p.S6yqmdnHlzY9lwiqm68IZgtuxj9KV1q'
    WHERE
    NOT EXISTS (
        SELECT 1 FROM public.user_system where id = 1
    ) ;