Dropzone.options.myDropzone = {
    paramName: 'file',
    maxFilesize: 200,
    accept: function(file, done) {
        if (file.name === "justinbieber.jpg") {
            done("Naha, you don't.");
        }
        else { done(); }
    }
};
