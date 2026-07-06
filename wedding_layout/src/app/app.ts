import { Component, signal } from '@angular/core';
import { Navbar } from './navbar/navbar';
import { Form } from './form/form';
@Component({
  selector: 'app-root',
  imports: [Navbar , Form],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {}
