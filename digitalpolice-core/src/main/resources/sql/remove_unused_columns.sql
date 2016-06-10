ALTER TABLE test_schema.category DROP COLUMN locale RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN "position" RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN description RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN color RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN image RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN visible RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN trusted RESTRICT;
ALTER TABLE test_schema.category DROP COLUMN imagethumb RESTRICT;

ALTER TABLE test_schema.region DROP COLUMN parent_id RESTRICT;
ALTER TABLE test_schema.region DROP COLUMN boundarieslong2 RESTRICT;
ALTER TABLE test_schema.region DROP COLUMN boundaries RESTRICT;