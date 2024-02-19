CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);


CREATE TABLE workouts (
                          workout_id SERIAL PRIMARY KEY,
                          user_id INTEGER REFERENCES users(user_id),
                          date DATE NOT NULL,
                          notes TEXT
);

CREATE TABLE exercises (
                           exercise_id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           body_part VARCHAR(50)
);

CREATE TABLE exercise_sets (
                               set_id SERIAL PRIMARY KEY,
                               workout_id INTEGER REFERENCES workouts(workout_id),
                               exercise_id INTEGER REFERENCES exercises(exercise_id),
                               repetitions INTEGER,
                               weight DOUBLE PRECISION
);


INSERT INTO exercises (name, body_part) VALUES ('Ab-Wheel Rollout', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Cable Crunch', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Crunch', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Crunch Machine', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Decline Crunch', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Dragon Flag', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Hanging Knee Raise', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Hanging Leg Raise', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Plank', 'Abs');
INSERT INTO exercises (name, body_part) VALUES ('Side Plank', 'Abs');


INSERT INTO exercises (name, body_part) VALUES ('Barbell Row', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Barbell Shrug', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Chin Up', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Deadlift', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Row', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Good Morning', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Hammer Strength Row', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Lat Pulldown', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Machine Shrug', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Neutral Chin Up', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Pendlay Row', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Pull Up', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Rack Pull', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Seated Cable Row', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('Straight-Arm Cable Pushdown', 'Back');
INSERT INTO exercises (name, body_part) VALUES ('T-Bar Row', 'Back');


INSERT INTO exercises (name, body_part) VALUES ('Barbell Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Cable Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Concentration Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Hammer Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Preacher Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('EZ-Bar Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('EZ-Bar Preacher Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Seated Incline Dumbbell Curl', 'Biceps');
INSERT INTO exercises (name, body_part) VALUES ('Seated Machine Curl', 'Biceps');



INSERT INTO exercises (name, body_part) VALUES ('Cable Crossover', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Decline Barbell Bench Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Decline Hammer Strength Chest Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Flat Barbell Bench Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Flat Dumbbell Bench Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Flat Dumbbell Fly', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Incline Barbell Bench Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Incline Dumbbell Bench Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Incline Dumbbell Fly', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Incline Hammer Strength Chest Press', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Push-Up', 'Chest');
INSERT INTO exercises (name, body_part) VALUES ('Seated Machine Fly', 'Chest');



INSERT INTO exercises (name, body_part) VALUES ('Barbell Calf Raise', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Barbell Front Squat', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Barbell Glute Bridge', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Barbell Squat', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Donkey Calf Raise', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Glute-Ham Raise', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Leg Extension Machine', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Leg Press', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Lying Leg Curl Machine', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Romanian Deadlift', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Seated Calf Raise Machine', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Seated Leg Curl Machine', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Standing Calf Raise Machine', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Stiff-Legged Deadlift', 'Legs');
INSERT INTO exercises (name, body_part) VALUES ('Sumo Deadlift', 'Legs');



INSERT INTO exercises (name, body_part) VALUES ('Arnold Dumbbell Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Behind The Neck Barbell Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Cable Face Pull', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Front Dumbbell Raise', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Hammer Strength Shoulder Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Lateral Dumbbell Raise', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Lateral Machine Raise', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Log Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('One-Arm Standing Dumbbell Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Overhead Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Push Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Rear Delt Dumbbell Raise', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Rear Delt Machine Fly', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Seated Dumbbell Lateral Raise', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Seated Dumbbell Press', 'Shoulders');
INSERT INTO exercises (name, body_part) VALUES ('Smith Machine Overhead Press', 'Shoulders');



INSERT INTO exercises (name, body_part) VALUES ('Cable Overhead Triceps Extension', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Close Grip Barbell Bench Press', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Dumbbell Overhead Triceps Extension', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('EZ-Bar Skullcrusher', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Lying Triceps Extension', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Parallel Bar Triceps Dip', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Ring Dip', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Rope Push Down', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('Smith Machine CLose Grip Bench Press', 'Triceps');
INSERT INTO exercises (name, body_part) VALUES ('V-Bar Push Down', 'Triceps');