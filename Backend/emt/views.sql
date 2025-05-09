CREATE MATERIALIZED VIEW accommodations_by_host AS
SELECT h.id AS host_profile_id, COUNT(a.id) AS num_accommodations
FROM host_profile h
         LEFT JOIN accommodation a ON h.id = a.host_profile_id
GROUP BY h.id;

CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT c.id AS country_id, COUNT(h.id) AS num_hosts
FROM country c
         LEFT JOIN host_profile h ON c.id = h.country_id
GROUP BY c.id;