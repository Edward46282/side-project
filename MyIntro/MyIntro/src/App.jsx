import myVideo from './assets/editedVideo.mp4'
import uniFlag from './assets/uottawa-logo.png'
import uniCampus from './assets/uottawa-campus.jpg'
import uniStem from './assets/uottawa-STEM.jpg'
import './App.css'
import { useState, useEffect } from 'react';
import SkillsCarousel from './SkillsCarousel'

const images = [
  uniCampus, uniFlag, uniStem
];

const projects = [
  {id: 1, title: "Messaging Program", desc: "Description 1"}, {id: 2, title: "Todo Android app", desc: "Description 2"}]

  function App() {
  return (
    <div className="main-container">
      <div className="content-wrapper">
        {/* Split Content Area */}
        <div id="mainScreen" className="split-view">
          {/* Left Side: Blue Box */}
          <div className="left-half">
            <div className="black-box">
              <h1>Edward Eo</h1>
              <div className="lineTag"></div>
              <p>info to come</p>
            </div>
          </div>

          {/* Right Side: Video */}
          <div className="right-half">
            <video
              autoPlay
              muted
              loop
              playsInline
              width="100%"
            >
              <source src={myVideo} type="video/mp4" />
              Your browser does not support the video tag.
            </video>
          </div>
        </div>
        <div className="gradient-spacer"></div>
        <div id="education" className="education-container">
          <div className="section-title">
            <h1 style={{ fontStyle: 'italic' }}>Education</h1>
          </div>
          <div className= "education-content">
            <div className = "uniPhoto">
              <ImageCard />
            </div>
            <div className = "uniDescription">
              <h3>Computer Science Student At</h3>
              <h2> University of Ottawa</h2>
              <div className="description-linespacer"></div>
              <p>Description coming</p>
            </div>
          </div>
        </div>
        
        <section id="skills" className="skills-container">
          <SkillsCarousel />
        </section>

        <section id="projects" className="projects-container">
          <div className="section-title">
            <h1 style={{ fontStyle: 'italic' }}>Projects</h1>
          </div>
          <ProjectCard />
        </section>

        <footer id="contact" className="footer">
          <p>This is the website to introduce me, Edward! (‾◡◝)</p>
          <a href="https://www.instagram.com/edward99929/" target="_blank">
            <i class="fa-brands fa-instagram" aria-label="Instagram logo"></i>
          </a>
          <a href="https://github.com/Edward46282" target="_blank">
            <i class="fa-brands fa-github" aria-label="Github logo"></i>
          </a>
          <a href="https://www.linkedin.com/in/edward-eo-74068b2b5/" target="_blank">
            <i class="fa-brands fa-linkedin" aria-label="Linkedin logo"></i>
          </a>
        </footer>
      </div>

      {/* The Right-Side Navbar */}
      <nav className="side-nav">
        <ul>
          <li><a href="#mainScreen">Main Screen</a></li>
          <li><a href="#education">Education</a></li>
          <li><a href="#skills">Skills</a></li>
          <li><a href="#projects">Projects</a></li>
          <li><a href="#contact">Contact</a></li>
        </ul>
      </nav>
    </div>
  )
}

function ImageCard() {
  const [index, setIndex] = useState(0);

  // Arrow function to go to next image
  const nextImage = () => {
    setIndex((prev) => (prev + 1) % images.length); // no-name function that takes prev index as a parameter
  };

  // Timer: Change every 15 seconds
  useEffect(() => {
    const timer = setInterval(nextImage, 15000);
    return () => clearInterval(timer); // Cleanup on unmount
  }, [index]);

  const nextIndex = (index + 1) % images.length;

  return (
    <div className="carousel-container">
      <div className="card-stack">
        {/* Main Image */}
        <img src={images[index]} key={index} className="main-card" alt="Active" />
        
        {/* Upcoming Tilted Image */}
        <img src={images[nextIndex]} key={nextIndex} className="next-card" alt="Next" />
      </div>
      
      <button onClick={nextImage} className="nav-button">Next Image</button>
    </div>
  );
}

function ProjectCard() {
  return (
    <div className = "project-Card-Container">
      {projects.map((project) => (
        <div key={project.id} className="project-card">
          <h3>{project.title}</h3>
          <p>{project.desc}</p>
        </div>
      ))}
    </div>
  );
}

export default App