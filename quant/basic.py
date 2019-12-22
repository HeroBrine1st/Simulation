from typing import List
from vector3d.point import Point
from vector3d.vector import Vector
from physics.link import Link


class Basic:
    position: Point
    pulse: Vector
    mass: int
    links: set[Link]

    def __init__(self, position: Point = Point(), pulse: Vector = Vector(), mass: int):
        self.position = position
        self.pulse = pulse
        self.mass = mass

    @property
    def velocity(self) -> Vector:
        return self.pulse * 1/self.mass

    @property
    def energy(self):
        return
