import React from "react";
import {Helmet} from "react-helmet";

function Head(){
    return(
        <Helmet>
            <link href="../styles/styles.css"/>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossOrigin="anonymous" />
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossOrigin="anonymous"></script>
            <title>Jasne!</title>
            <link rel="stylesheet" href="../styles/style.css"/>
            <link rel="stylesheet" href="../styles/namari-color.css"/>
            <link rel="stylesheet" href="../styles/font-awesome.min.css"/>
            <link href="../styles/animate.css" rel="stylesheet" type="text/css"/>
            <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'/>
        </Helmet>
    )
}
export default Head;