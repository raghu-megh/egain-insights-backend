-- src/main/resources/data.sql

-- Seed company_info
INSERT INTO company_info(domain, name, industry, location, employee_count) VALUES
  ('acme.com',             'Acme Corp',           'Manufacturing',   'Los Angeles, CA',   1200),
  ('globex.com',           'Globex Inc.',         'Technology',      'San Francisco, CA', 5000),
  ('initech.com',          'Initech LLC',         'Software',        'Dallas, TX',        250),
  ('umbrella.com',         'Umbrella Corp',       'Biotech',         'Raccoon City, Raccoon County', 10000),
  ('starkindustries.com',  'Stark Industries',    'Defense',         'Los Angeles, CA',   15000),
  ('wayneenterprises.com', 'Wayne Enterprises',   'Technology',      'Gotham City, NJ',   8000),
  ('hooli.com',            'Hooli',               'Technology',      'Palo Alto, CA',    20000);

-- Seed visitors
INSERT INTO visitors(id, session_id, ip, domain, first_seen, last_seen, visit_count) VALUES
  (1, 'sess-abc123', '192.0.2.10',    'acme.com',             '2025-06-01T09:00:00', '2025-06-01T09:15:00', 3),
  (2, 'sess-def456', '198.51.100.23', 'globex.com',           '2025-06-02T11:30:00', '2025-06-02T12:00:00', 5),
  (3, 'sess-ghi789', '203.0.113.42',  'initech.com',          '2025-06-03T14:45:00', '2025-06-03T15:05:00', 2),
  (4, 'sess-jkl012', '203.0.113.55',  'umbrella.com',         '2025-06-04T08:10:00', '2025-06-04T08:25:00', 4),
  (5, 'sess-mno345', '198.51.100.77', 'starkindustries.com',  '2025-06-05T13:00:00', '2025-06-05T13:05:00', 1),
  (6, 'sess-pqr678', '192.0.2.88',    'wayneenterprises.com', '2025-06-06T10:30:00', '2025-06-06T11:00:00', 6),
  (7, 'sess-stu901', '203.0.113.99',  'hooli.com',            '2025-06-07T09:15:00', '2025-06-07T09:45:00', 8);

-- Seed page_views
INSERT INTO page_views(id, visitor_id, timestamp, url, referrer) VALUES
  -- Visitor 1 (Acme)
  (1,  1, '2025-06-01T09:00:00', '/home',           NULL),
  (2,  1, '2025-06-01T09:05:00', '/products',       '/home'),
  (3,  1, '2025-06-01T09:15:00', '/contact',        '/products'),

  -- Visitor 2 (Globex)
  (4,  2, '2025-06-02T11:30:00', '/home',           NULL),
  (5,  2, '2025-06-02T11:35:00', '/solutions',      '/home'),
  (6,  2, '2025-06-02T11:45:00', '/pricing',        '/solutions'),
  (7,  2, '2025-06-02T11:55:00', '/contact',        '/pricing'),
  (8,  2, '2025-06-02T12:00:00', '/thank-you',      '/contact'),

  -- Visitor 3 (Initech)
  (9,  3, '2025-06-03T14:45:00', '/home',           NULL),
  (10, 3, '2025-06-03T14:55:00', '/support',        '/home'),

  -- Visitor 4 (Umbrella)
  (11, 4, '2025-06-04T08:10:00', '/home',          NULL),
  (12, 4, '2025-06-04T08:15:00', '/research',      '/home'),
  (13, 4, '2025-06-04T08:20:00', '/careers',       'https://linkedin.com'),
  (14, 4, '2025-06-04T08:25:00', '/contact',       '/careers'),

  -- Visitor 5 (Stark Industries)
  (15, 5, '2025-06-05T13:00:00', '/home',          NULL),

  -- Visitor 6 (Wayne Enterprises)
  (16, 6, '2025-06-06T10:30:00', '/home',          NULL),
  (17, 6, '2025-06-06T10:35:00', '/products',      '/home'),
  (18, 6, '2025-06-06T10:40:00', '/enterprise',    '/products'),
  (19, 6, '2025-06-06T10:45:00', '/pricing',       '/enterprise'),
  (20, 6, '2025-06-06T10:50:00', '/contact',       '/pricing'),
  (21, 6, '2025-06-06T11:00:00', '/blog',          '/contact'),

  -- Visitor 7 (Hooli)
  (22, 7, '2025-06-07T09:15:00', '/home',          NULL),
  (23, 7, '2025-06-07T09:20:00', '/products',      '/home'),
  (24, 7, '2025-06-07T09:25:00', '/developers',    '/products'),
  (25, 7, '2025-06-07T09:30:00', '/docs',          '/developers'),
  (26, 7, '2025-06-07T09:35:00', '/pricing',       '/docs'),
  (27, 7, '2025-06-07T09:40:00', '/contact',       '/pricing'),
  (28, 7, '2025-06-07T09:45:00', '/thank-you',     '/contact');
