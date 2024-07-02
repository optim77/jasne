START TRANSACTION;
INSERT INTO users (id, username, name, surname, specialization, email, password, bio, created_at, verified, enable,
                   role)
VALUES (100, 'jdoe', 'John', 'Doe', 'Software Engineer', 'jdoe@example.com',
        '$2a$10$m8OV1qejFzmvKzncVIayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Passionate about coding and technology.',
        '2024-06-29 10:00:00', true, true, 'USER'),
       (101, 'asmith', 'Alice', 'Smith', 'Data Scientist', 'asmith@example.com',
        '$2a$10$m8OV1qejFzmvKzncVIayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Loves working with data and analytics.',
        '2024-06-28 09:30:00', true, true, 'ADMIN'),
       (102, 'bjackson', 'Bob', 'Jackson', 'Web Developer', 'bjackson@example.com',
        '$2a$10$m8OV1qejFzmvKzncVIayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG',
        'Enjoys creating responsive and dynamic websites.', '2024-06-27 08:45:00', true, true, 'USER'),
       (103, 'cmiller', 'Cathy', 'Miller', 'Project Manager', 'cmiller@example.com',
        '$2a$10$m8OV1qejFzmvKzncVIayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Experienced in managing tech projects.',
        '2024-06-26 07:15:00', true, true, 'USER'),
       (104, 'mjohnson', 'Michael', 'Johnson', 'Cybersecurity Expert', 'mjohnson@example.com',
        '$2a$10$Z2G1UzFZMv.QKZvcX4ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Protecting data and networks.',
        '2024-06-25 06:45:00', true, true, 'USER'),
       (105, 'lwilson', 'Laura', 'Wilson', 'AI Researcher', 'lwilson@example.com',
        '$2a$10$G1J3RrF2zm.YKjvcU5ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG',
        'Exploring the frontiers of artificial intelligence.', '2024-06-24 05:30:00', true, true, 'USER'),
       (106, 'dlee', 'David', 'Lee', 'Network Administrator', 'dlee@example.com',
        '$2a$10$H1J3S1f1zn.KJzvcV5ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG',
        'Ensuring the integrity and reliability of network systems.', '2024-06-23 04:15:00', true, true, 'USER'),
       (107, 'krodriguez', 'Karen', 'Rodriguez', 'UX Designer', 'krodriguez@example.com',
        '$2a$10$J1H3U1g3xn.LKzvcX5ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Designing user-centered interfaces.',
        '2024-06-22 03:00:00', true, true, 'USER'),
       (108, 'gmartinez', 'Gabriel', 'Martinez', 'Mobile Developer', 'gmartinez@example.com',
        '$2a$10$K1J4V2h2zn.MLzvcY5ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG',
        'Building mobile applications for Android and iOS.', '2024-06-21 02:45:00', true, true, 'USER'),
       (109, 'aevans', 'Anna', 'Evans', 'Data Analyst', 'aevans@example.com',
        '$2a$10$Q1J5K3l1zm.QKZvcV6ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Data enthusiast.', '2024-06-20 10:00:00', true,
        true, 'USER'),
       (110, 'brichards', 'Ben', 'Richards', 'Software Tester', 'brichards@example.com',
        '$2a$10$L2J4M4m2zm.NLZvcW6ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Passionate about quality.',
        '2024-06-19 09:30:00', true, true, 'USER'),
       (111, 'mikejohnson', 'Mike', 'Johnson', 'Content Writer', 'mikejohnson@example.com',
        '$2a$10$N8J7M8m1zm.YKZvcV5ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Love writing about various topics.',
        '2024-06-18 10:00:00', true, true, 'USER'),
       (112, 'lwhite', 'Linda', 'White', 'Graphic Designer', 'lwhite@example.com',
        '$2a$10$O9K8L9n2zm.NLZvcV7ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Creative and passionate about design.',
        '2024-06-17 09:30:00', true, true, 'USER'),
       (113, 'lwhite1', 'Linda1', 'White1', 'Graphic Designer', 'lwhite123@example.com',
        '$2a$10$O9K8L9n2zm.NLZvcV7ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Creative 1and passionate about design.',
        '2024-06-17 09:30:00', true, true, 'USER'),
       (114, 'waddd', 'Wey', 'Lee', 'Graphic Designerz', 'wl12@example.com',
        '$2a$10$O9K8L9n2zm.NLZvcV7ayOusHeMv4E0fhg5oUQq2xbwiY0q9mZL3JG', 'Creative 1and passionate about design.',
        '2024-06-17 09:30:00', true, true, 'USER');


INSERT INTO category(id, name, news_counter)
VALUES (1, 'Science', 0),
       (2, 'Politic', 0),
       (3, 'Tech', 0),
       (4, 'Discussions', 0),
       (5, 'Media', 0),
       (6, 'News', 0),
       (7, 'Economy', 0),
       (8, 'Misc', 0);

INSERT INTO news(id, created_at, description, title, votes, author, category_id)
VALUES (100, '2024-06-29 10:00:00',
        'Before everyone puts their pitchforks down, I certainly don''t think the debate went well. However, we''re still four months from the election, and Biden sounded SO. MUCH. SHARPER the following day at the North Carolina rally. It could be that he actually had a cold the night of the debate and that''s why he was a bit off his game. Additionally, while both Biden and Trump will have more money than they can realistically spend on their campaigns, the Biden campaign had its best fundraising hour of the cycle from 11 PM to 12 AM on the night of the debate. Could it be that most of the people dooming about his performance are worried because they want him to win and will vote for him? Of course, maybe I''m just in denial. The stakes of this election are just so high that I can''t give up four months before the votes are counted.',
        'Could the debate not actually hurt Biden that much?', 4, 100, 2),
       (200, '2024-06-30 10:00:00',
        'It’s an odd question I know but I haven’t been able to find a good examination for the etymology here. Thank you for any help!',
        'Ants are called formic. Does formic originate to describe ants which move like soldiers, or are formations called that because soldiers at the time move like ants?',
        5, 103, 8),
       (300, '2024-06-29 12:00:00',
        'The recent advancements in AI have led to new breakthroughs in machine learning and natural language processing. Researchers are optimistic about the future applications of these technologies.',
        'AI Breakthroughs and Future Applications', 10, 107, 3),
       (400, '2024-06-30 11:00:00',
        'A major cybersecurity breach has affected thousands of users. Experts are urging everyone to update their passwords and enable two-factor authentication.',
        'Major Cybersecurity Breach Affects Thousands', 8, 106, 7),
       (500, '2024-06-30 12:30:00',
        'A new study shows the economic impact of the pandemic on small businesses. Many are struggling to recover and need more support.',
        'Economic Impact of the Pandemic on Small Businesses', 6, 109, 7),
       (600, '2024-06-30 13:00:00',
        'The latest trends in web development are focused on improving performance and accessibility. Developers are adopting new tools and frameworks to enhance user experience.',
        'Latest Trends in Web Development', 7, 104, 3),
       (700, '2024-06-30 14:00:00',
        'Political analysts discuss the upcoming election and the potential outcomes. The stakes are high, and both parties are gearing up for a tough battle.',
        'Political Analysts Discuss Upcoming Election', 5, 102, 2);


INSERT INTO comment(id, content, created_at, votes, author, news)
VALUES (100,
        'The name "formic" comes from the Latin word "formica," which means "ant." Formic acid is named after ants because they have high concentrations of this compound in their venom. (1) Formic acid - Wikipedia. https://en.wikipedia.org/wiki/Formic_acid. (2) Formica - Wikipedia. https://en.wikipedia.org/wiki/Formica. (3) ADW: Formicidae: INFORMATION. https://animaldiversity.org/accounts/Formicidae/. (4) Formic acid - American Chemical Society. https://www.acs.org/molecule-of-the-week/archive/f/formic-acid.html. (5) Formic acid | Formula, Preparation, Uses, & Facts | Britannica. https://www.britannica.com/science/formic-acid. (6) en.wikipedia.org. https://en.wikipedia.org/wiki/Formic_acid.',
        '2024-07-01 10:00:00', 4, 105, 200),
       (200,
        'He was obviously not feeling well during the debate. He sounded great in Raleigh yesterday. If one bad debate cost the election then we have too many fickle people voting for the person and not the administration.',
        '2024-07-01 10:00:00', 7, 102, 100),
       (300, 'I don''t think the debate changed much. People already know Biden isn''t a great public speaker.',
        '2024-07-01 10:00:00', 2, 104, 100),
       (400,
        'The advancements in AI are truly remarkable. I can''t wait to see how these technologies will be integrated into our daily lives.',
        '2024-07-01 11:00:00', 3, 107, 300),
       (500,
        'Cybersecurity is more important than ever. Everyone should take this breach as a reminder to strengthen their online security.',
        '2024-07-01 12:00:00', 6, 106, 400),
       (600,
        'Small businesses are the backbone of our economy. It''s crucial that we support them during these challenging times.',
        '2024-07-01 13:00:00', 4, 109, 500),
       (700,
        'Web development is evolving rapidly. It''s exciting to see the new tools and techniques that are being developed.',
        '2024-07-01 14:00:00', 5, 104, 600),
       (800,
        'The upcoming election is going to be critical. It''s important for everyone to stay informed and participate in the voting process.',
        '2024-07-01 15:00:00', 7, 102, 700);

-- Science Category (ID: 1)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (800, '2024-06-30 09:00:00', 'Recent discoveries in quantum computing are set to revolutionize technology.',
        'Quantum Computing Breakthroughs', 12, 111, 1),
       (900, '2024-06-30 09:30:00', 'New study reveals the secrets of black hole formation.',
        'Black Hole Formation Secrets Unveiled', 9, 112, 1),
       (1000, '2024-06-30 10:00:00', 'Exploring the potential of gene editing to cure diseases.',
        'Gene Editing: The Future of Medicine', 15, 111, 1),
       (1100, '2024-06-30 10:30:00', 'Climate change impacts on ocean life are more severe than previously thought.',
        'Climate Change and Ocean Life', 11, 112, 1),
       (1200, '2024-06-30 11:00:00', 'Advances in renewable energy technology are making a big difference.',
        'Renewable Energy Advances', 14, 111, 1);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (900, 'Quantum computing is going to change the world.', '2024-07-01 10:00:00', 5, 102, 800),
       (1000, 'Black holes are fascinating. Great read!', '2024-07-01 10:30:00', 3, 103, 900),
       (1100, 'Gene editing offers so much potential for medical advancements.', '2024-07-01 11:00:00', 7, 104, 1000),
       (1200, 'The impact on ocean life is alarming. We need to act now.', '2024-07-01 11:30:00', 6, 105, 1100),
       (1300, 'Renewable energy is the key to a sustainable future.', '2024-07-01 12:00:00', 8, 106, 1200);

-- Politic Category (ID: 2)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (1300, '2024-06-30 12:00:00', 'The latest developments in international trade agreements.',
        'International Trade Agreements Update', 10, 102, 2),
       (1400, '2024-06-30 12:30:00', 'A look into the upcoming elections and their potential impact.',
        'Upcoming Elections: What to Expect', 9, 105, 2),
       (1500, '2024-06-30 13:00:00', 'New policies aim to tackle climate change effectively.',
        'Policies for Climate Change', 12, 103, 2),
       (1600, '2024-06-30 13:30:00', 'An analysis of the recent legislative changes.', 'Recent Legislative Changes', 8, 104,
        2),
       (1700, '2024-06-30 14:00:00', 'The role of technology in modern politics.', 'Technology in Politics', 11, 106, 2);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (1400, 'Trade agreements are crucial for economic stability.', '2024-07-01 13:00:00', 5, 111, 1300),
       (1500, 'Elections are always unpredictable.', '2024-07-01 13:30:00', 4, 112, 1400),
       (1600, 'Climate change policies need to be a priority.', '2024-07-01 14:00:00', 6, 111, 1500),
       (1700, 'Legislative changes affect everyone.', '2024-07-01 14:30:00', 3, 112, 1600),
       (1800, 'Technology will shape the future of politics.', '2024-07-01 15:00:00', 7, 111, 1700);

-- Tech Category (ID: 3)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (1800, '2024-06-30 15:00:00', 'The latest trends in artificial intelligence and machine learning.',
        'AI and Machine Learning Trends', 13, 107, 3),
       (1900, '2024-06-30 15:30:00', 'Breakthroughs in blockchain technology and their applications.',
        'Blockchain Technology Breakthroughs', 12, 104, 3),
       (2000, '2024-06-30 16:00:00', 'How virtual reality is changing the gaming industry.', 'Virtual Reality in Gaming',
        10, 103, 3),
       (2100, '2024-06-30 16:30:00', 'The impact of 5G on mobile communications.', '5G and Mobile Communications', 11, 106,
        3),
       (2200, '2024-06-30 17:00:00', 'Exploring the potential of quantum computing.', 'Quantum Computing Potential', 14,
        107, 3);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (1900, 'AI is advancing at an incredible pace.', '2024-07-01 15:00:00', 6, 107, 1800),
       (2000, 'Blockchain will revolutionize many industries.', '2024-07-01 15:30:00', 5, 104, 1900),
       (2100, 'Virtual reality makes gaming more immersive.', '2024-07-01 16:00:00', 4, 103, 2000),
       (2200, '5G will improve connectivity worldwide.', '2024-07-01 16:30:00', 7, 106, 2100),
       (2300, 'Quantum computing has limitless potential.', '2024-07-01 17:00:00', 8, 107, 2200);

-- Discussions Category (ID: 4)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (2300, '2024-06-30 18:00:00', 'A heated debate on the ethics of AI.', 'Ethics of AI', 10, 105, 4),
       (2400, '2024-06-30 18:30:00', 'Discussions on the future of renewable energy.', 'Future of Renewable Energy', 9, 103,
        4),
       (2500, '2024-06-30 19:00:00', 'Exploring different perspectives on climate change.',
        'Perspectives on Climate Change', 12, 104, 4),
       (2600, '2024-06-30 19:30:00', 'Debating the impact of technology on society.', 'Impact of Technology on Society',
        11, 106, 4),
       (2700, '2024-06-30 20:00:00', 'A forum on the role of education in the modern world.', 'Role of Education', 8, 107,
        4);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (2400, 'AI ethics is a complex and important topic.', '2024-07-01 18:00:00', 6, 105, 2300),
       (2500, 'Renewable energy discussions are crucial for our future.', '2024-07-01 18:30:00', 5, 103, 2400),
       (2600, 'Climate change affects everyone differently.', '2024-07-01 19:00:00', 4, 104, 2500),
       (2700, 'Technology can have both positive and negative impacts.', '2024-07-01 19:30:00', 3, 106, 2600),
       (2800, 'Education is the foundation of a progressive society.', '2024-07-01 20:00:00', 7, 107, 2700);

-- Media Category (ID: 5)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (2800, '2024-06-30 21:00:00', 'The evolution of media in the digital age.', 'Digital Media Evolution', 11, 102, 5),
       (2900, '2024-06-30 21:30:00', 'How social media influences public opinion.', 'Social Media and Public Opinion', 10,
        111, 5),
       (3000, '2024-06-30 22:00:00', 'The impact of streaming services on traditional media.',
        'Streaming vs. Traditional Media', 12, 112, 5),
       (3100, '2024-06-30 22:30:00', 'Exploring the rise of podcasts and their popularity.', 'The Rise of Podcasts', 9, 102,
        5),
       (3200, '2024-06-30 23:00:00', 'The role of media in shaping political discourse.', 'Media and Political Discourse',
        13, 111, 5);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (2900, 'Digital media has changed how we consume information.', '2024-07-01 21:00:00', 6, 102, 2800),
       (3000, 'Social media has a huge impact on public opinion.', '2024-07-01 21:30:00', 5, 111, 2900),
       (3100, 'Streaming services offer more convenience and choice.', '2024-07-01 22:00:00', 4, 112, 3000),
       (3200, 'Podcasts are a great way to learn and be entertained.', '2024-07-01 22:30:00', 7, 102, 3100),
       (3300, 'Media plays a critical role in politics.', '2024-07-01 23:00:00', 8, 111, 3200);

-- News Category (ID: 6)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (3300, '2024-07-01 00:00:00', 'Breaking news on the latest economic developments.', 'Economic Developments', 14, 106,
        6),
       (3400, '2024-07-01 00:30:00', 'Headline: Major political event shakes the nation.', 'Major Political Event', 13, 105,
        6),
       (3500, '2024-07-01 01:00:00', 'Top story: Advances in medical research.', 'Medical Research Advances', 12, 104, 6),
       (3600, '2024-07-01 01:30:00', 'Breaking: Significant changes in technology sector.', 'Technology Sector Changes',
        11, 103, 6),
       (3700, '2024-07-01 02:00:00', 'Headline: Environmental issues take center stage.',
        'Environmental Issues Highlighted', 10, 102, 6);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (3400, 'Economic news affects everyone.', '2024-07-01 00:00:00', 5, 106, 3300),
       (3500, 'Political events can have a lasting impact.', '2024-07-01 00:30:00', 4, 105, 3400),
       (3600, 'Medical research is advancing rapidly.', '2024-07-01 01:00:00', 7, 104, 3500),
       (3700, 'Technology sector changes are exciting to follow.', '2024-07-01 01:30:00', 6, 103, 3600),
       (3800, 'Environmental issues need more attention.', '2024-07-01 02:00:00', 8, 102, 3700);

-- Economy Category (ID: 7)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (3800, '2024-07-01 03:00:00', 'Analyzing the recent trends in global markets.', 'Global Market Trends', 11, 107, 7),
       (3900, '2024-07-01 03:30:00', 'The impact of new economic policies on growth.', 'Economic Policies and Growth', 10,
        106, 7),
       (4000, '2024-07-01 04:00:00', 'Exploring the effects of inflation on everyday life.', 'Effects of Inflation', 12,
        105, 7),
       (4100, '2024-07-01 04:30:00', 'How startups are driving economic innovation.', 'Startups and Innovation', 13, 104,
        7),
       (4200, '2024-07-01 05:00:00', 'The role of the gig economy in today’s market.', 'Gig Economy Role', 14, 103, 7);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (3900, 'Global market trends are fascinating to watch.', '2024-07-01 03:00:00', 6, 107, 3800),
       (4000, 'Economic policies can greatly affect growth.', '2024-07-01 03:30:00', 5, 106, 3900),
       (4100, 'Inflation has a direct impact on our lives.', '2024-07-01 04:00:00', 7, 105, 4000),
       (4200, 'Startups are key to innovation.', '2024-07-01 04:30:00', 8, 104, 4100),
       (4300, 'The gig economy is reshaping the market.', '2024-07-01 05:00:00', 4, 103, 4200);

-- Misc Category (ID: 8)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (4300, '2024-07-01 06:00:00', 'A look at the latest trends in various fields.', 'Latest Trends Across Fields', 9,
        111, 8),
       (4400, '2024-07-01 06:30:00', 'Exploring different hobbies and their benefits.', 'Benefits of Various Hobbies', 8,
        112, 8),
       (4500, '2024-07-01 07:00:00', 'The importance of mental health awareness.', 'Mental Health Awareness', 10, 111, 8),
       (4600, '2024-07-01 07:30:00', 'A guide to effective time management.', 'Effective Time Management', 11, 112, 8),
       (4700, '2024-07-01 08:00:00', 'Exploring the world of creative arts.', 'World of Creative Arts', 12, 111, 8);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (440, 'Staying updated on trends is important.', '2024-07-01 06:00:00', 5, 111, 4300),
       (450, 'Hobbies can greatly enhance our lives.', '2024-07-01 06:30:00', 4, 112, 4400),
       (460, 'Mental health awareness is crucial.', '2024-07-01 07:00:00', 7, 111, 4500),
       (470, 'Time management is key to productivity.', '2024-07-01 07:30:00', 6, 112, 4600),
       (480, 'Creative arts inspire and enrich our lives.', '2024-07-01 08:00:00', 8, 111, 4700);
-- Additional news and comments for each category
-- Science Category (ID: 1)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (4800, '2024-07-01 09:00:00', 'Discoveries in nanotechnology could revolutionize medicine.',
        'Nanotechnology in Medicine', 13, 113, 1),
       (4900, '2024-07-01 09:30:00', 'New research on the effects of space travel on the human body.',
        'Space Travel and Human Health', 11, 114, 1),
       (5000, '2024-07-01 10:00:00', 'The potential of CRISPR technology to eradicate genetic diseases.',
        'CRISPR and Genetic Diseases', 14, 113, 1),
       (5100, '2024-07-01 10:30:00', 'Studying the impact of climate change on polar ice caps.',
        'Climate Change and Polar Ice Caps', 10, 114, 1),
       (5200, '2024-07-01 11:00:00', 'Exploring the mysteries of dark matter.', 'Mysteries of Dark Matter', 12, 113, 1);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (490, 'Nanotechnology will be a game-changer in healthcare.', '2024-07-02 10:00:00', 7, 102, 4800),
       (5000, 'Space travel research is crucial for long-term missions.', '2024-07-02 10:30:00', 5, 103, 4900),
       (510, 'CRISPR technology has immense potential.', '2024-07-02 11:00:00', 8, 104, 5000),
       (520, 'The melting of polar ice caps is concerning.', '2024-07-02 11:30:00', 6, 105, 5100),
       (530, 'Dark matter remains one of the biggest mysteries in science.', '2024-07-02 12:00:00', 7, 106, 5200);

-- Politic Category (ID: 2)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (5300, '2024-07-01 12:00:00', 'The implications of new immigration policies.', 'New Immigration Policies', 11, 102,
        2),
       (5400, '2024-07-01 12:30:00', 'Debate on healthcare reform continues.', 'Healthcare Reform Debate', 10, 105, 2),
       (5500, '2024-07-01 13:00:00', 'Analyzing the latest diplomatic efforts.', 'Latest Diplomatic Efforts', 12, 103, 2),
       (5600, '2024-07-01 13:30:00', 'The influence of lobbying on policy making.', 'Lobbying and Policy Making', 9, 104,
        2),
       (5700, '2024-07-01 14:00:00', 'The impact of tax reforms on the economy.', 'Tax Reforms and Economy', 13, 106, 2);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (540, 'Immigration policies affect millions of lives.', '2024-07-02 12:00:00', 5, 111, 5300),
       (550, 'Healthcare reform is a critical issue.', '2024-07-02 12:30:00', 4, 112, 5400),
       (560, 'Diplomatic efforts are essential for global peace.', '2024-07-02 13:00:00', 6, 111, 5500),
       (570, 'Lobbying can have both positive and negative effects.', '2024-07-02 13:30:00', 3, 112, 5600),
       (580, 'Tax reforms can stimulate economic growth.', '2024-07-02 14:00:00', 7, 111, 5700);

-- Tech Category (ID: 3)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (5800, '2024-07-01 15:00:00', 'The rise of autonomous vehicles.', 'Autonomous Vehicles', 14, 107, 3),
       (5900, '2024-07-01 15:30:00', 'Advancements in cybersecurity to protect data.', 'Cybersecurity Advancements', 13,
        104, 3),
       (6000, '2024-07-01 16:00:00', 'The future of wearable technology.', 'Wearable Technology Future', 11, 103, 3),
       (6100, '2024-07-01 16:30:00', 'Exploring the potential of edge computing.', 'Edge Computing Potential', 12, 106, 3),
       (6200, '2024-07-01 17:00:00', 'The impact of AI on job markets.', 'AI and Job Markets', 10, 107, 3);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (590, 'Autonomous vehicles will change transportation forever.', '2024-07-02 15:00:00', 8, 107, 5800),
       (6000, 'Cybersecurity is more important than ever.', '2024-07-02 15:30:00', 5, 104, 5900),
       (610, 'Wearable technology is becoming more integrated into our lives.', '2024-07-02 16:00:00', 7, 103, 6000),
       (620, 'Edge computing can significantly improve processing speeds.', '2024-07-02 16:30:00', 6, 106, 6100),
       (630, 'AI will create new job opportunities while disrupting others.', '2024-07-02 17:00:00', 8, 107, 6200);

-- Discussions Category (ID: 4)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (6300, '2024-07-01 18:00:00', 'Debating the pros and cons of universal basic income.',
        'Universal Basic Income Debate', 10, 105, 4),
       (6400, '2024-07-01 18:30:00', 'The role of technology in education.', 'Technology in Education', 9, 103, 4),
       (6500, '2024-07-01 19:00:00', 'Exploring the future of remote work.', 'Future of Remote Work', 12, 104, 4),
       (6600, '2024-07-01 19:30:00', 'Discussing mental health in the workplace.', 'Mental Health at Work', 11, 106, 4),
       (6700, '2024-07-01 20:00:00', 'The ethics of genetic modification.', 'Genetic Modification Ethics', 8, 107, 4);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (640, 'Universal basic income could reduce poverty.', '2024-07-02 18:00:00', 6, 105, 6300),
       (650, 'Technology is transforming education.', '2024-07-02 18:30:00', 5, 103, 6400),
       (660, 'Remote work is here to stay.', '2024-07-02 19:00:00', 7, 104, 6500),
       (670, 'Mental health should be prioritized in the workplace.', '2024-07-02 19:30:00', 6, 106, 6600),
       (680, 'Genetic modification raises many ethical questions.', '2024-07-02 20:00:00', 8, 107, 6700);

-- Media Category (ID: 5)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (6800, '2024-07-01 21:00:00', 'The evolution of online news platforms.', 'Evolution of Online News', 9, 113, 5),
       (6900, '2024-07-01 21:30:00', 'Impact of 24-hour news cycles on journalism.', '24-Hour News Cycles', 11, 114, 5),
       (7000, '2024-07-01 22:00:00', 'How influencers are changing marketing.', 'Influencers and Marketing', 10, 113, 5),
       (7100, '2024-07-01 22:30:00', 'The growth of citizen journalism.', 'Citizen Journalism', 12, 114, 5),
       (7200, '2024-07-01 23:00:00', 'Challenges facing traditional media.', 'Challenges for Traditional Media', 8, 113,
        5);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (690, 'Online news platforms offer instant access to information.', '2024-07-02 21:00:00', 5, 113, 6800),
       (7000, '24-hour news cycles can lead to burnout for journalists.', '2024-07-02 21:30:00', 6, 114, 6900),
       (710, 'Influencers have become key players in marketing.', '2024-07-02 22:00:00', 4, 113, 7000),
       (720, 'Citizen journalism provides diverse perspectives.', '2024-07-02 22:30:00', 7, 114, 7100),
       (730, 'Traditional media must adapt to survive.', '2024-07-02 23:00:00', 6, 113, 7200);

-- News Category (ID: 6)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (7300, '2024-07-01 00:00:00', 'Breaking news on new scientific discoveries.', 'New Scientific Discoveries', 13, 107,
        6),
       (7400, '2024-07-01 00:30:00', 'Headline: Major breakthrough in renewable energy.', 'Renewable Energy Breakthrough',
        14, 106, 6),
       (7500, '2024-07-01 01:00:00', 'Top story: Technological advancements in AI.', 'AI Advancements', 12, 105, 6),
       (7600, '2024-07-01 01:30:00', 'Breaking: Significant policy changes announced.', 'Policy Changes Announced', 11, 104,
        6),
       (7700, '2024-07-01 02:00:00', 'Headline: Advances in medical technology.', 'Medical Technology Advances', 10, 103,
        6);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (740, 'Scientific discoveries are always exciting.', '2024-07-02 00:00:00', 6, 107, 7300),
       (750, 'Renewable energy is the future.', '2024-07-02 00:30:00', 7, 106, 7400),
       (760, 'AI advancements will revolutionize industries.', '2024-07-02 01:00:00', 5, 105, 7500),
       (770, 'Policy changes can have widespread effects.', '2024-07-02 01:30:00', 4, 104, 7600),
       (780, 'Medical technology is advancing rapidly.', '2024-07-02 02:00:00', 8, 103, 7700);

-- Economy Category (ID: 7)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (7800, '2024-07-01 03:00:00', 'Analyzing the latest stock market trends.', 'Stock Market Trends', 11, 111, 7),
       (7900, '2024-07-01 03:30:00', 'The impact of global trade agreements.', 'Global Trade Agreements', 10, 112, 7),
       (8000, '2024-07-01 04:00:00', 'Exploring the rise of cryptocurrency.', 'Rise of Cryptocurrency', 13, 111, 7),
       (8100, '2024-07-01 04:30:00', 'How economic policies affect small businesses.',
        'Economic Policies and Small Businesses', 9, 112, 7),
       (8200, '2024-07-01 05:00:00', 'The role of central banks in economic stability.', 'Central Banks and Stability',
        14, 111, 7);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (790, 'Stock market trends are influenced by various factors.', '2024-07-02 03:00:00', 5, 111, 7800),
       (8000, 'Global trade agreements shape the economy.', '2024-07-02 03:30:00', 4, 112, 7900),
       (810, 'Cryptocurrency is becoming more mainstream.', '2024-07-02 04:00:00', 7, 111, 8000),
       (820, 'Economic policies can make or break small businesses.', '2024-07-02 04:30:00', 6, 112, 8100),
       (830, 'Central banks play a crucial role in economic stability.', '2024-07-02 05:00:00', 8, 111, 8200);

-- Misc Category (ID: 8)
INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (8300, '2024-07-01 06:00:00', 'Exploring different types of meditation and their benefits.', 'Types of Meditation',
        9, 113, 8),
       (8400, '2024-07-01 06:30:00', 'The impact of travel on personal growth.', 'Travel and Personal Growth', 8, 114, 8),
       (8500, '2024-07-01 07:00:00', 'How to cultivate a growth mindset.', 'Cultivating a Growth Mindset', 10, 113, 8),
       (8600, '2024-07-01 07:30:00', 'The benefits of lifelong learning.', 'Benefits of Lifelong Learning', 11, 114, 8),
       (8700, '2024-07-01 08:00:00', 'Exploring different cultural traditions.', 'Different Cultural Traditions', 12, 113,
        8);

INSERT INTO comment (id, content, created_at, votes, author, news)
VALUES (840, 'Meditation offers numerous health benefits.', '2024-07-02 06:00:00', 5, 113, 8300),
       (850, 'Traveling helps us grow and learn.', '2024-07-02 06:30:00', 4, 114, 8400),
       (860, 'A growth mindset can lead to personal success.', '2024-07-02 07:00:00', 7, 113, 8500),
       (870, 'Lifelong learning keeps the mind sharp.', '2024-07-02 07:30:00', 6, 114, 8600),
       (880, 'Cultural traditions are important to preserve.', '2024-07-02 08:00:00', 8, 113, 8700);

INSERT INTO news (id, created_at, description, title, votes, author, category_id)
VALUES (8900, '2024-07-02 08:00:00', 'Next-Gen AI Chips Revolutionize Computing',
        'New AI chips are significantly improving processing speeds for machine learning applications.', 13, 108, 3),
       (9000, '2024-07-02 08:30:00', 'Quantum Computing Breakthrough Achieved',
        'Researchers have achieved a major breakthrough in quantum computing, potentially revolutionizing the tech industry.',
        14, 109, 3),
       (9100, '2024-07-02 09:00:00', 'New VR Headset Released',
        'The latest VR headset offers unprecedented levels of immersion and realism.', 13, 110, 3),
       (9200, '2024-07-02 09:30:00', 'Blockchain Technology Adoption Rises',
        'More companies are adopting blockchain technology to enhance security and transparency.', 12, 111, 3),
       (9300, '2024-07-02 10:00:00', '5G Networks Expand Globally',
        '5G network rollout continues to accelerate, bringing faster internet speeds to more regions.', 11, 112, 3),
       (9400, '2024-07-02 10:30:00', 'New Advances in Battery Technology',
        'Recent advancements in battery technology are promising longer-lasting and faster-charging devices.', 10, 113,
        3),
       (9500, '2024-07-02 11:00:00', 'AI-Powered Personal Assistants Improve',
        'AI-powered personal assistants are becoming more sophisticated, offering enhanced functionality.', 9, 114, 3),
       (9600, '2024-07-02 11:30:00', 'Cybersecurity Threats on the Rise',
        'Cybersecurity experts warn of increasing threats and emphasize the importance of robust security measures.', 8,
        112, 3),
       (9700, '2024-07-02 12:00:00', 'Self-Driving Cars Get Regulatory Approval',
        'Several countries have given regulatory approval for self-driving cars, paving the way for wider adoption.', 7,
        111, 3),
       (9800, '2024-07-02 12:30:00', 'Innovations in Wearable Tech',
        'New wearable tech devices are offering more health and fitness tracking capabilities.', 6, 103, 3),
       (9900, '2024-07-02 13:00:00', 'Tech Giants Invest in Renewable Energy',
        'Major tech companies are investing heavily in renewable energy to reduce their carbon footprint.', 5, 105, 3),
       (10000, '2024-07-02 13:30:00', 'AR Technology Enhances Education',
        'Augmented Reality (AR) technology is being increasingly used to enhance educational experiences.', 4, 104, 3),
       (10100, '2024-07-02 14:00:00', 'Cloud Computing Services Expand',
        'Cloud computing services are expanding, offering more scalable and efficient solutions.', 3, 102, 3),
       (10200, '2024-07-02 14:30:00', 'Edge Computing Gains Traction',
        'Edge computing is gaining traction as a way to process data closer to the source, reducing latency.', 2, 106,
        3),
       (10300, '2024-07-02 15:00:00', 'Robotic Process Automation in Business',
        'Robotic Process Automation (RPA) is being increasingly adopted by businesses to streamline operations.', 1, 108,
        3);

INSERT INTO comment(id, content, created_at, votes, author, news)
VALUES
    (100000,
     'Formic acid is derived from the Latin word "formica," meaning "ant," due to its presence in ant venom. (1) Formic acid - Wikipedia. https://en.wikipedia.org/wiki/Formic_acid. (2) Formica - Wikipedia. https://en.wikipedia.org/wiki/Formica. (3) ADW: Formicidae: INFORMATION. https://animaldiversity.org/accounts/Formicidae/. (4) Formic acid - American Chemical Society. https://www.acs.org/molecule-of-the-week/archive/f/formic-acid.html. (5) Formic acid | Formula, Preparation, Uses, & Facts | Britannica. https://www.britannica.com/science/formic-acid. (6) en.wikipedia.org. https://en.wikipedia.org/wiki/Formic_acid.',
     '2024-07-01 10:00:00', 4, 105, 200),
    (1010,
     'I appreciate the detailed explanation.',
     '2024-07-01 10:05:00', 4, 105, 200),
    (1020,
     'Thank you for this informative post.',
     '2024-07-01 10:10:00', 4, 105, 200),
    (1030,
     'Formic acid, named after ants, is found in high concentrations in their venom. (1) Formic acid - Wikipedia. https://en.wikipedia.org/wiki/Formic_acid. (2) Formica - Wikipedia. https://en.wikipedia.org/wiki/Formica. (3) ADW: Formicidae: INFORMATION. https://animaldiversity.org/accounts/Formicidae/. (4) Formic acid - American Chemical Society. https://www.acs.org/molecule-of-the-week/archive/f/formic-acid.html. (5) Formic acid | Formula, Preparation, Uses, & Facts | Britannica. https://www.britannica.com/science/formic-acid. (6) en.wikipedia.org. https://en.wikipedia.org/wiki/Formic_acid.',
     '2024-07-01 10:15:00', 4, 105, 200),
    (1040,
     'Great explanation!',
     '2024-07-01 10:20:00', 4, 105, 200),
    (1050,
     'Interesting question, thanks for asking.',
     '2024-07-01 10:25:00', 4, 105, 200),
    (1060,
     'The term "formic" is derived from "formica," the Latin word for "ant." Ants contain high levels of formic acid. (1) Formic acid - Wikipedia. https://en.wikipedia.org/wiki/Formic_acid. (2) Formica - Wikipedia. https://en.wikipedia.org/wiki/Formica. (3) ADW: Formicidae: INFORMATION. https://animaldiversity.org/accounts/Formicidae/. (4) Formic acid - American Chemical Society. https://www.acs.org/molecule-of-the-week/archive/f/formic-acid.html. (5) Formic acid | Formula, Preparation, Uses, & Facts | Britannica. https://www.britannica.com/science/formic-acid. (6) en.wikipedia.org. https://en.wikipedia.org/wiki/Formic_acid.',
     '2024-07-01 10:30:00', 4, 105, 200),
    (1070,
     'This is very helpful, thank you!',
     '2024-07-01 10:35:00', 4, 105, 200),
    (1080,
     'Excellent question, it made me curious too.',
     '2024-07-01 10:40:00', 4, 105, 200),
    (1090,
     'Formic acid gets its name from "formica," the Latin word for "ant," due to its presence in ant venom. (1) Formic acid - Wikipedia. https://en.wikipedia.org/wiki/Formic_acid. (2) Formica - Wikipedia. https://en.wikipedia.org/wiki/Formica. (3) ADW: Formicidae: INFORMATION. https://animaldiversity.org/accounts/Formicidae/. (4) Formic acid - American Chemical Society. https://www.acs.org/molecule-of-the-week/archive/f/formic-acid.html. (5) Formic acid | Formula, Preparation, Uses, & Facts | Britannica. https://www.britannica.com/science/formic-acid. (6) en.wikipedia.org. https://en.wikipedia.org/wiki/Formic_acid.',
     '2024-07-01 10:45:00', 4, 105, 200),
    (11000,
     'Well explained, thanks!',
     '2024-07-01 10:50:00', 4, 105, 200),
    (1110,
     'Good question, thanks for raising it.',
     '2024-07-01 10:55:00', 4, 105, 200),
    (1120,
     'Very insightful question, I learned a lot.',
     '2024-07-01 11:00:00', 4, 105, 200),
    (113,
     'That was a great question!',
     '2024-07-01 11:05:00', 4, 105, 200),
    (114,
     'Thanks for the great question.',
     '2024-07-01 11:10:00', 4, 105, 200),
    (115,
     'Interesting, thanks for asking.',
     '2024-07-01 11:15:00', 4, 105, 200),
    (116,
     'Good to see someone asking this.',
     '2024-07-01 11:20:00', 4, 105, 200),
    (117,
     'Thanks for bringing this up.',
     '2024-07-01 11:25:00', 4, 105, 200),
    (118,
     'Glad you asked this question.',
     '2024-07-01 11:30:00', 4, 105, 200),
    (119,
     'I was wondering about this too.',
     '2024-07-01 11:35:00', 4, 105, 200),
    (120,
     'Thanks for the good question.',
     '2024-07-01 11:40:00', 4, 105, 200),
    (121,
     'This is a valuable question.',
     '2024-07-01 11:45:00', 4, 105, 200),
    (122,
     'Thanks for the insightful question.',
     '2024-07-01 11:50:00', 4, 105, 200),
    (123,
     'I appreciate this question.',
     '2024-07-01 11:55:00', 4, 105, 200),
    (124,
     'Good question, very relevant.',
     '2024-07-01 12:00:00', 4, 105, 200),
    (125,
     'Thanks for such a good question.',
     '2024-07-01 12:05:00', 4, 105, 200),
    (126,
     'I enjoyed thinking about this question.',
     '2024-07-01 12:10:00', 4, 105, 200),
    (127,
     'Nice question, thanks for posting.',
     '2024-07-01 12:15:00', 4, 105, 200),
    (128,
     'Really good question, appreciated.',
     '2024-07-01 12:20:00', 4, 105, 200);

COMMIT;