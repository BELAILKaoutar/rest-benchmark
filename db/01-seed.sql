-- 2000 categories
INSERT INTO category(code, name)
SELECT
    format('CAT%04s', gs::text) AS code,
    format('Category %s', gs::text) AS name
FROM generate_series(1, 2000) AS gs;

-- 100 000 items
WITH base AS (
    SELECT id AS category_id FROM category
)
INSERT INTO item(sku, name, price, stock, category_id, updated_at)
SELECT
    format('SKU%06s', gs::text),
    format('Item %s', gs::text),
    round((random()*990 + 10)::numeric, 2),
    (random()*200)::int,
    (SELECT category_id FROM base ORDER BY random() LIMIT 1),
    NOW() - ((random()*30)::int || ' days')::interval
FROM generate_series(1, 100000) gs;
