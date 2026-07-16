import { Component } from '@angular/core';
import { QrGenerate } from '../qr-generate/qr-generate';

@Component({
  selector: 'nav-bar',
  standalone: true,
  imports: [ QrGenerate ],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {}
