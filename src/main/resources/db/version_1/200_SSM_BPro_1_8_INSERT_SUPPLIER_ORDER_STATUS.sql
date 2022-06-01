USE pro_business_db;

INSERT INTO MODULE (MOD_LABEL) VALUE ('SUPPLIER_ORDER');

INSERT IGNORE INTO STATUS (NAME, MOD_ID) VALUES ('CREATED', 3),
                                                ('CONFIRMED', 3),
                                                ('SHIPPED', 3),
                                                ('CANCELED', 3),
                                                ('PAID', 3);

COMMIT;