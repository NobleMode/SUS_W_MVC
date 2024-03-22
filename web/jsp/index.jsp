<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">SSU University Main Page</h5>
                        <div>Student</div>
                        <div> 
                            <a  href="StudentControllerURL?service=listAll" class="btn btn-primary">List all Student</a>
                            <a  href="studentCourseControllerURL?service=listAll" class="btn btn-primary">List all Student Courses</a>
                            <a  href="StudentDepControllerURL?service=listAll" class="btn btn-primary">List all Student Dependance</a>
                        </div>

                        <div>Teacher</div>
                        <div>
                            <a  href="TeacherControllerURL?service=listAll" class="btn btn-primary">List all Teacher</a>
                            <a  href="TeacherCourseControllerURL?service=listAll" class="btn btn-primary">List all Teacher Courses</a>
                            <a  href="TeacherDepControllerURL?service=listAll" class="btn btn-primary">List all Teacher Dependance</a>
                        </div>
                       
                        <div>Course</div>
                        <div>
                            <a  href="CourseControllerURL?service=listAll" class="btn btn-primary">List all Course</a>
                            <a  href="MajorControllerURL?service=listAll" class="btn btn-primary">List all Major</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<style>
    .card-title {
        margin-bottom: 1em;
    }
</style>
</html>