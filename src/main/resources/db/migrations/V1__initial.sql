-- Insert sample data into the users table
INSERT INTO users (Id, full_name, email, password, username, created_by, created_at, updated_by,
                   updated_at, version)
VALUES (uuid_generate_v4(), 'Alice Johnson', 'alice.johnson@example.com', 'password123', 'alicej',
        'admin', NOW(), 'admin', NOW(), 1),
       (uuid_generate_v4(), 'Bob Smith', 'bob.smith@example.com', 'password456', 'bobsmith',
        'admin', NOW(), 'admin', NOW(), 1),
       (uuid_generate_v4(), 'Charlie Brown', 'charlie.brown@example.com', 'password789', 'charlieb',
        'admin', NOW(), 'admin', NOW(), 1),
       (uuid_generate_v4(), 'Diana Prince', 'diana.prince@example.com', 'password101', 'dianap',
        'admin', NOW(), 'admin', NOW(), 1);
