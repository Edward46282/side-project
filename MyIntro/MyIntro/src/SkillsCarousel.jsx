import { useRef } from "react";
import { motion, useTransform, useScroll } from "framer-motion";
import "./Skills.css";

const SKILLS = [
  { id: 1, title: "Code in well-known programming language like Python and Java", desc: "Expert in hooks, context, and performance." },
  { id: 2, title: "Data structure", desc: "Scalable backend architecture and APIs." },
  { id: 3, title: "Web development", desc: "Creating fluid, 60fps web animations." },
  { id: 4, title: "Networking", desc: "Writing type-safe, maintainable code." },
];

const SkillsCarousel = () => {
  const targetRef = useRef(null);
  
  // Hook into the vertical scroll of the targetRef
  const { scrollYProgress } = useScroll({
    target: targetRef,
  });

  // Map scroll progress 0=top, 1=past the section -> horizontal movement (0 to -30%)
  // -70% move the cards to the left as you scroll down.
  const x = useTransform(scrollYProgress, [0, 1], ["0%", "-30%"]);

  return (
    <section ref={targetRef} className="skills-section">
      <h1 className="skills-title">Skills</h1>
      <div className="sticky-wrapper">
        <motion.div style={{ x }} className="cards-container">
          {SKILLS.map((skill) => (
            <div key={skill.id} className="skill-card">
              <h3>{skill.title}</h3>
              <p>{skill.desc}</p>
            </div>
          ))}
        </motion.div>
      </div>
    </section>
  );
};

export default SkillsCarousel;