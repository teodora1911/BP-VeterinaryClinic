USE `BPProjektniZadatak` ;


# CITIES
INSERT INTO city (Name, ZIPCode) VALUES ('Banja Luka', '78100'), ('Laktasi', '78250'), ('Mrkonjic Grad', '70260'), ('Gradiska', '78400'),
										('Srbac', '78420'), ('Prnjavor', '78430'), ('Bijeljina', '76300'), ('Ugljevik', '76330'),
                                        ('Brcko', '76101'), ('Samac', '76230'), ('Doboj', '74101'), ('Teslic', '74270'),
                                        ('Derventa', '74400'), ('Modrica', '74480'), ('Prijedor', '79101'), ('Novi Grad', '79220'),
                                        ('Kozarska Dubica', '79240'), ('Kotor Varos', '78220'), ('Celinac', '78230'), ('Drinic', '79290');

# SPECIALIZATIONS
INSERT INTO specialization (Title, Description) VALUES ('Kardiologija', NULL), ('Interna medicina', NULL), ('Neurologija', NULL), ('Onkologija', NULL),
													   ('Nutricionizam', NULL), ('Virusologija', NULL), ('Imunologija', NULL), ('Bakteriologija / Mikologija', NULL),
                                                       ('Parazitologija', NULL), ('Anatomska patologija', NULL), ('Klinicka patologija', NULL), ('Epidemiologija', NULL),
                                                       ('Hirurgija', NULL), ('Dentalna medicina', NULL), ('Ortopedija', NULL), ('Radiologija', NULL);
         
# VETERINARIANS
INSERT INTO veterinarian (Name, Surname, Email, PhoneNumber, HomeNumber, HomeAddress, Username, Password) 
				  VALUES ('Marko', 'Markovic', 'marko.markovic@gmail.com', '066123456', null, null, 'markom', 'marko'),
						 ('Simo', 'Simic', 'simo.simic@gmail.com', '066456789', null, null, 'simos', 'simo'),
                         ('Marija', 'Marinkovic', 'marija.marinkovic@gmail.com', '066147258', null, null, 'marijam', 'marija'),
                         ('Nikolina', 'Ninkovic', 'nikolina.ninkovic@gmail.com', '066258369', null, null, 'nina', 'nikolina'),
                         ('Sara', 'Saric', 'sara.saric@gmail.com', '066159357', null, null, 'saras', 'sara');

# SERVICES
INSERT INTO service(Name, Cost) VALUES ('Preventiva', 50), ('Hirurgija', 100), ('Ortopedija', 50), ('Laboratorija', 25), ('Internisticki pregled', 50),
									   ('Radioloska dijagnostika', 50), ('Ultrazvucna dijagnostika', 50), ('Stomatoloska intervencija', 50),
                                       ('Reprodukcija', 20), ('Vakcinacija', 20);


# PROVIDES 
INSERT INTO provides(Veterinarian, Service) VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
												   (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (2, 10),
                                                   (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10),
                                                   (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6), (4, 7), (4, 8), (4, 9), (4, 10),
												   (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7), (5, 8), (5, 9), (5, 10);

# 	MANUFACTURER
INSERT INTO manufacturer (Name, Description) VALUES ('Abaxis', null), ('ACell', null), ('Boehringer Ingelheim', null), ('Centaur', null), 
													('Ceva Animal Health LLC', null), ('Dechra Veterinary Products', null), ('Elanco Animal Health', null), 
                                                    ('Neogen', null), ('Mars Pet Care', null), ('Vetoquinol', null), ('Virbac', null), ('Zoetis', null);
 
# MEDICINE TYPE
INSERT INTO medicinetype (Type) VALUES ('Antibiotici'), ('Nesteroidna antiinflamatorna sredstva'), ('Lijekovi protiv bolova'), ('Steroidi'),
									   ('Antiparazitni lijekovi'), ('Lijekovi za stabilizaciju ponasanja i sedativi'), ('Hormoni'), ('Kemoterapeutici'),
                                       ('Vitamini'), ('Vakcine');

# MEDICINE
INSERT INTO medicine VALUES (1101, 'Gentamicin', 8, ' ', 3, 50, 1, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1102, 'Streptamicin', 12, ' ', 6, 50, 1, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1103, 'Eritromicin', 4, ' ', 4, 50, 1, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1104, 'Amoksicilin', 5, ' ', 2, 50, 1, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1105, 'Penicilin', 10, ' ', 11, 50, 1, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1201, 'Ibuprofen', 3, ' ', 11, 50, 2, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1202, 'Ketoprofen', 5, ' ', 7, 50, 2, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1203, 'Diklofenak', 3, ' ', 1, 50, 2, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1204, 'Eritrokoksib', 8, ' ', 12,50, 2, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1205, 'Naproksen', 7, ' ', 10, 50, 2, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1301, 'Morfin', 54, ' ', 3, 50, 3, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1302, 'Metadon', 48, ' ', 3, 50, 3, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1303, 'Kodein', 62, ' ', 8, 50, 3, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1304, 'Petidin', 78, ' ', 10,50, 3, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1305, 'Fentanil', 40, ' ', 7, 50, 3, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1401, 'Deksametazon', 23, ' ', 2, 50, 4, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1402, 'Klobetazon', 19, ' ', 4, 50, 4, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1403, 'Dezonid', 15, ' ', 3, 50, 4, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1404, 'Prednizolon', 25, ' ', 5,50, 4, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1405, 'Hidrokortizon', 12, ' ', 7, 50, 4, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1501, 'Prazikvantal', 10, ' ', 8, 50, 5, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1502, 'Ivermektin', 14, ' ', 2, 50, 5, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1503, 'Albendazon', 13, ' ', 11, 50, 5, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1504, 'Mebendazol', 21, ' ', 2, 50, 5, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1601, 'Bensedin', 10, ' ', 5, 50, 6, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1602, 'Benzodiapezin', 12, ' ', 1, 50, 6, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1603, 'Barbiturat', 9, ' ', 6, 50, 6, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1604, 'Fluoksetin', 13, ' ', 7, 50, 6, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1605, 'Sertalin', 17, ' ', 12, 50, 6, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1701, 'Insulin', 24, ' ', 5, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1702, 'Tiroksin', 28, ' ', 1, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1703, 'Kortizol', 19, ' ', 5, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1704, 'Progesteron', 30, ' ', 11, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1705, 'Testosteron', 21, ' ', 7, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1706, 'HCG', 29, ' ', 7, 50, 7, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1801, 'Aciklovir', 81, ' ', 3, 50, 8, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1802, 'Metrodinazol', 92, ' ', 6, 50, 8, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1803, 'Podofilin', 88, ' ', 10, 50, 8, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1804, 'Srebrosulfadizin', 78, ' ', 6, 50, 8, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1805, '5-Fluorouracil', 95, ' ', 3, 50, 8, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (1901, 'Tiamin', 12, ' ', 1, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1902, 'Biotin', 15, ' ', 12, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1903, 'Folna kiselina', 10, ' ', 8, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1904, 'Niacin', 17, ' ', 7, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1905, 'Kobalamin', 16, ' ', 5, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1906, 'Riboflavin', 14, ' ', 12, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (1907, 'Askorbinaska kiselina', 19, ' ', 1, 50, 9, '2021-09-26 12:59:36', '2022-09-26 12:59:36'),
                            (2101, 'Protiv bjesnila', 55, ' ', 2, 50, 10, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (2102, 'Protiv zaraznog hepatitisa', 48, ' ', 11, 50, 10, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (2103, 'Protiv helmintoze', 50, ' ', 10, 50, 10, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (2104, 'Protiv leptospiroze', 42, ' ', 12, 50, 10, '2021-09-26 12:59:36', '2022-09-26 12:59:36'), (2105, 'Protiv krpelja', 60, ' ', 5, 50, 10, '2021-09-26 12:59:36', '2022-09-26 12:59:36');
                            
# SERVICE - MEDICINE
INSERT INTO servicehasmedicine VALUES (1101, 1, 2), (1201, 1, 2), (1701, 1, 2), 
									  (1303, 2, 5), (1504, 2, 5), (1202, 2, 3), (1801, 2, 1), (1905, 2, 5),
                                      (1305, 3, 2), (1104, 3, 1),
                                      (1602, 4, 2), (1704, 4, 2), (1906, 4, 5),
                                      (1403, 5, 2), (1603, 5, 2), (1701, 5, 2),
                                      (1405, 8, 2), (1302, 8, 2), (1102, 8, 2),
                                      (1105, 9, 5), (1301, 9, 2),
                                      (2101, 10, 2), (2103, 10, 2), (2104, 10, 2);

# GENDERS
INSERT INTO gender (Name) VALUES ('Zenski'), ('Muski');

# SPECIES 
INSERT INTO species (Name) VALUES ('Macka'), ('Pas'), ('Konj'), ('Hrcak'), ('Kornjaca');

#BREEDS
INSERT INTO breed (Name, Species) VALUES ('Persijska', 1), ('Britanska kratkodlaka', 1), ('Bengalska', 1), ('Sijamska', 1), ('Sfinks', 1), ('Sibirska', 1), ('Savana', 1), ('Turska angora', 1), ('Himalajska', 1),
										 ('Belgijski ovcar', 2), ('Buldog', 2), ('Njemacki ovcar', 2), ('Zlatni retriver', 2), ('Civava', 2), ('Francuski buldog', 2), ('Bul-terijer', 2), ('Avganistanski hrt', 2), ('Cau-cau', 2), ('Si-cu', 2),
                                         ('Arapski', 3), ('Frizijski', 3), ('Apaluza', 3), ('Mustang', 3), ('Ciganski', 3), ('Haflinger', 3), ('Perseron', 3), ('Bretonski', 3), ('Islandski', 3), ('Hucul', 3), ('Cob', 3),
                                         ('Sirijski', 4), ('Bijeli ruski', 4), ('Kineski', 4), ('Sivi', 4), ('Veliki dugorepi', 4), ('Zlatni', 4),
                                         ('Barska', 5), ('Rijecna', 5), ('Crvenouha', 5), ('Cancara', 5);
                                         
#PAYMENTS
INSERT INTO payment(Type) VALUES ('Karticno'), ('Gotovina');