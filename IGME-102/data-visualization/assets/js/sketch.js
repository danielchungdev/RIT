
let data;
let values = [1, 1, false, 1]
let legend;

function preload(){
    let file = '../../data/PlacesHealthLocalities.json';
    data = loadJSON(file);
    legend = loadImage("/assets/images/legend.png");
}

function setup(){
    createCanvas(2000, 1000);
    background("black");
    image(legend, 1000,20, legend.width/ 2, legend.height/2);
    noLoop();
    createP("Locality Health visualization controls: ");
    let select = createSelect();
    select.option("Map median asthma, high blood pressure & whiteness", 1)
    select.option("Map lost all teeth & public assistance", 2)
    select.option("Map fair/poor physical health & depression", 3)
    select.option("Map mammography & median income", 4)
    createP("")
    let select2 = createSelect();
    select2.option("For all localities", 1)
    select2.option("For < 8K localities", 2)
    select2.option("For > 300K localities", 3)
    select2.option("For > 30% non-white localities", 4)

    let checkbox = createCheckbox("Show names");
    checkbox.checked(false)

    createSpan("Visualization Scale: ")
    let slider = createSlider(1, 5.5);
    slider.value(1);

    select.changed( () => {
        values[0] = parseInt(select.value());
        redraw();
    });

    select2.changed( () => {
        values[1] = parseInt(select2.value());
        redraw();
    });
    values.push(slider);
    slider.input(updateSlider);
    
}

function updateSlider(){
    values[3] = values[4].value();
    redraw();   
}


function drawCircle(size, lon, lat, whitepeople, asthma, bloodpressure, multiplier){
    let longitude = 3000 + (parseInt(lon) * 20);
    let latitude = 1400 + ((parseInt(lat) * -1)* 20);
    let whiteShade = (whitepeople * 360) / size;
    size = Math.sqrt(size * 0.001) * multiplier;
    width = size / 4
    if (size < 10){
        size = 20;
        width = 20;
    }
    fill(0,0,255,63);
    arc(longitude, latitude, size, width, radians(whiteShade), radians(360));
    fill(128,128,128,63);
    arc(longitude, latitude, size, width, 0, radians(whiteShade));
    fill("yellow");
    noStroke();
    rect(longitude - size / 5, latitude - width + width / 2, 2, asthma );
    if (bloodpressure < 10){
        fill("blue");
    }
    if (bloodpressure > 30){
        fill("magenta");
    }
    else {
        fill("#5535C5");
    }
    square(longitude, latitude, 3);
}

function drawSecond(lon, lat, population, lostallteeths, publicAssistant, multiplier){
    let longitude = 3000 + (parseInt(lon) * 20);
    let latitude = 1400 + ((parseInt(lat) * -1)* 20);
    size =Math.sqrt(population * 0.001) * multiplier;
    if (size < 10){
        size = 20;
    }
    fill(125,125,125, 60)
    rect(longitude, latitude, size, 10);
    fill(125, 125, lostallteeths)
    square(longitude, latitude, 5);
    //Orange is less, purple is more
    fill(200, 125, publicAssistant)
    circle(longitude + size / 2, latitude + 5, 5);
}   

function drawThird(lon, lat, population, physicalHealth, depression){
    let longitude = 3000 + (parseInt(lon) * 20);
    let latitude = 1400 + ((parseInt(lat) * -1)* 20);
    size = Math.sqrt(population * 0.01);
    fill(125,125,125, 60);
    rect(longitude, latitude, size, 10);
    //red is less, pink is more
    fill(255, 0, physicalHealth, 60);
    square(longitude + size / 3, latitude, 5);
    //Cyan is less white is more
    fill(depression, 255 ,255, 60);
    circle(longitude + 2.5 + size / 3, latitude + 7.5, 5);
}

function drawFourth(lon, lat, population, mmammography, medianIncome, multiplier){
    let longitude = 3000 + (parseInt(lon) * 20);
    let latitude = 1400 + ((parseInt(lat) * -1)* 20);
    size = Math.sqrt(population * 0.01) * multiplier;
    fill(125,125,125, 60);
    circle(longitude, latitude, size);
    //red is less, pink is more
    fill(255, 0, mmammography, 60);
    circle(longitude, latitude + 2, 4);
    //Cyan is less white is more
    fill(medianIncome, 255 ,255, 60);
    circle(longitude, latitude - 2, 4);
}

function draw(){
    for (const index in data){
        if('lon' in data[index] && 'lat' in data[index]){
            let temp = data[index];
            if(values[0] === 1){
                if (values[1] === 1){
                    if (temp.population > 0){
                        drawCircle(temp.population, temp.lon, temp.lat, temp.white, temp.outcomes["Current Asthma"], temp.outcomes["High Blood Pressure"], values[3]);
                    }
                }
                if (values[1] === 2){
                    if (temp.population < 8000){
                        drawCircle(temp.population, temp.lon, temp.lat, temp.white, temp.outcomes["Current Asthma"], temp.outcomes["High Blood Pressure"], values[3]);
                    }
                }
                if (values[1] === 3){
                    if (temp.population > 300000){
                        drawCircle(temp.population, temp.lon, temp.lat, temp.white, temp.outcomes["Current Asthma"], temp.outcomes["High Blood Pressure"], values[3]);
                    }
                }
                if (values[1] === 4){
                    let totalWhites = temp.white;
                    let percent30 = 30 * totalWhites / 100;
                    if (temp.white > percent30){
                        drawCircle(temp.population, temp.lon, temp.lat, temp.white, temp.outcomes["Current Asthma"], temp.outcomes["High Blood Pressure"], values[3]);
                    }
                }
            }
            if (values[0] === 2){
                if (values[1] === 1){
                    if (temp.population > 0){
                        drawSecond(temp.lon, temp.lat, temp.population, temp.outcomes["All Teeth Lost"], temp.assisted ,values[3]);
                    }
                }
                if (values[1] === 2){
                    if (temp.population < 8000){
                        drawSecond(temp.lon, temp.lat, temp.population, temp.outcomes["All Teeth Lost"], temp.assisted ,values[3]);

                    }
                }
                if (values[1] === 3){
                    if (temp.population > 300000){
                        drawSecond(temp.lon, temp.lat, temp.population, temp.outcomes["All Teeth Lost"], temp.assisted ,values[3]);

                    }
                }
                if (values[1] === 4){
                    let totalWhites = temp.white;
                    let percent30 = 30 * totalWhites / 100;
                    if (temp.white > percent30){
                        drawSecond(temp.lon, temp.lat, temp.population, temp.outcomes["All Teeth Lost"], temp.assisted ,values[3]);
                    }
                }
            }
            if (values[0] === 3){
                if (values[1] === 1){
                    if (temp.population > 0){
                        drawThird(temp.lon, temp.lat, temp.population, temp.statuses["Physical Health"] , temp.outcomes["Depression"]);
                    }
                }
                if (values[1] === 2){
                    if (temp.population < 8000){
                        drawThird(temp.lon, temp.lat, temp.population, temp.statuses["Physical Health"] , temp.outcomes["Depression"]);
                    }
                }
                if (values[1] === 3){
                    if (temp.population > 300000){
                        drawThird(temp.lon, temp.lat, temp.population, temp.statuses["Physical Health"] , temp.outcomes["Depression"]);
                    }
                }
                if (values[1] === 4){
                    let totalWhites = temp.white;
                    let percent30 = 30 * totalWhites / 100;
                    if (temp.white > percent30){
                        drawThird(temp.lon, temp.lat, temp.population, temp.statuses["Physical Health"] , temp.outcomes["Depression"]);
                    }
                }
            }
            if (values[0] === 4){
                if (values[1] === 1){
                    if (temp.population > 0){
                        drawFourth(temp.lon, temp.lat, temp.population, temp.preventions["Mammography"], temp.medianIncome, values[3]);
                    }
                }
                if (values[1] === 2){
                    if (temp.population < 8000){
                        drawFourth(temp.lon, temp.lat, temp.population, temp.preventions["Mammography"], temp.medianIncome, values[3]);
                    }
                }
                if (values[1] === 3){
                    if (temp.population > 300000){
                        drawFourth(temp.lon, temp.lat, temp.population, temp.preventions["Mammography"], temp.medianIncome, values[3]);
                    }
                }
                if (values[1] === 4){
                    let totalWhites = temp.white;
                    let percent30 = 30 * totalWhites / 100;
                    if (temp.white > percent30){
                        drawFourth(temp.lon, temp.lat, temp.population, temp.preventions["Mammography"], temp.medianIncome, values[3]);
                    }
                }
            }
        }
    }
}