/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculodeareas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author DOCENTE
 */


    // 1. CLASE BASE ABSTRACTA (Abstracción y Encapsulamiento)
    abstract class FiguraGeometrica {
        private String nombre;

        public FiguraGeometrica(String nombre) {
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        // Método abstracto que cada subclase debe implementar obligatoriamente
        public abstract double calcularArea();
    }

    // 2. SUBCLASES CONCRETAS (Herencia y Polimorfismo)
    class Circulo extends FiguraGeometrica {
        private double radio;

        public Circulo(double radio) {
            super("Círculo");
            if (radio <= 0) {
                throw new IllegalArgumentException("El radio debe ser mayor que cero.");
            }
            this.radio = radio;
        }

        @Override
        public double calcularArea() {
            return Math.PI * Math.pow(radio, 2);
        }
    }

    class Rectangulo extends FiguraGeometrica {
        private double base;
        private double altura;

        public Rectangulo(double base, double altura) {
            super("Rectángulo");
            if (base <= 0 || altura <= 0) {
                throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
            }
            this.base = base;
            this.altura = altura;
        }

        // Constructor protegido para reutilizar en Cuadrado
        protected Rectangulo(String nombre, double base, double altura) {
            super(nombre);
            if (base <= 0 || altura <= 0) {
                throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
            }
            this.base = base;
            this.altura = altura;
        }

        @Override
        public double calcularArea() {
            return base * altura;
        }
    }

    class Cuadrado extends Rectangulo {
        // Un cuadrado es un rectángulo con lados iguales
        public Cuadrado(double lado) {
            super("Cuadrado", lado, lado);
        }
    }

    class Triangulo extends FiguraGeometrica {
        private double base;
        private double altura;

        public Triangulo(double base, double altura) {
            super("Triángulo");
            if (base <= 0 || altura <= 0) {
                throw new IllegalArgumentException("Las dimensiones deben ser mayores que cero.");
            }
            this.base = base;
            this.altura = altura;
        }

        @Override
        public double calcularArea() {
            return (base * altura) / 2.0;
        }
    }

    // Clase auxiliar para registrar el historial
    class RegistroHistorial {
        private String nombreFigura;
        private double area;

        public RegistroHistorial(String nombreFigura, double area) {
            this.nombreFigura = nombreFigura;
            this.area = area;
        }

        @Override
        public String toString() {
            return String.format("%s: %.2f", nombreFigura, area);
        }
    }

    // 3. CLASE PRINCIPAL DEL PROGRAMA
    public class Calculodeareas {
        private List<RegistroHistorial> historial = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void mostrarMenu() {
            System.out.println("\n==============================");
            System.out.println("   CÁLCULO DE ÁREAS (POO)     ");
            System.out.println("==============================");
            System.out.println("1. Círculo");
            System.out.println("2. Cuadrado");
            System.out.println("3. Rectángulo");
            System.out.println("4. Triángulo");
            System.out.println("5. Ver áreas calculadas");
            System.out.println("6. Salir");
        }

        public void ejecutar() {
            boolean continuar = true;

            while (continuar) {
                mostrarMenu();
                System.out.print("\nSeleccione una opción (1-6): ");
                String opcion = scanner.nextLine().trim();

                if (opcion.equals("6")) {
                    System.out.println("\n¡Gracias por usar el programa!");
                    continuar = false;
                    break;
                }

                try {
                    FiguraGeometrica figura = null;

                    switch (opcion) {
                        case "1":
                            System.out.print("Ingrese el radio del círculo: ");
                            double radio = Double.parseDouble(scanner.nextLine());
                            figura = new Circulo(radio);
                            break;

                        case "2":
                            System.out.print("Ingrese el lado del cuadrado: ");
                            double lado = Double.parseDouble(scanner.nextLine());
                            figura = new Cuadrado(lado);
                            break;

                        case "3":
                            System.out.print("Ingrese la base del rectángulo: ");
                            double baseR = Double.parseDouble(scanner.nextLine());
                            System.out.print("Ingrese la altura del rectángulo: ");
                            double alturaR = Double.parseDouble(scanner.nextLine());
                            figura = new Rectangulo(baseR, alturaR);
                            break;

                        case "4":
                            System.out.print("Ingrese la base del triángulo: ");
                            double baseT = Double.parseDouble(scanner.nextLine());
                            System.out.print("Ingrese la altura del triángulo: ");
                            double alturaT = Double.parseDouble(scanner.nextLine());
                            figura = new Triangulo(baseT, alturaT);
                            break;

                        case "5":
                            mostrarHistorial();
                            continue;

                        default:
                            System.out.println("Opción no válida. Intente nuevamente.");
                            continue;
                    }

                    // Polimorfismo en acción:
                    double area = figura.calcularArea();
                    System.out.printf("\n[Resultado] El área del %s es: %.2f\n", figura.getNombre(), area);
                    historial.add(new RegistroHistorial(figura.getNombre(), area));

                } catch (NumberFormatException e) {
                    System.out.println("\nError de entrada: Por favor ingrese un número válido.");
                } catch (IllegalArgumentException e) {
                    System.out.println("\nError de validación: " + e.getMessage());
                }
            }
        }

        private void mostrarHistorial() {
            System.out.println("\n--- Historial de Cálculos ---");
            if (historial.isEmpty()) {
                System.out.println("No hay cálculos registrados aún.");
            } else {
                for (int i = 0; i < historial.size(); i++) {
                    System.out.printf("%d. %s\n", (i + 1), historial.get(i));
                }
            }
        }

        public static void main(String[] args) {
            Calculodeareas app = new Calculodeareas();
            app.ejecutar();
        }
    }
  

