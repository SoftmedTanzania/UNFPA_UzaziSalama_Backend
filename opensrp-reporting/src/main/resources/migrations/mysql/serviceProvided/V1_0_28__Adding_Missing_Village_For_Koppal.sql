INSERT INTO report.dim_location(state, district, taluka, phc, subCenter, village) (SELECT 'Karnataka', 'Koppal', 'Koppal', (SELECT ID FROM report.dim_phc WHERE phcIdentifier='phc_hirebommanhal'), 'chikbommanhal', 'hiresulikeri_thanda'); 